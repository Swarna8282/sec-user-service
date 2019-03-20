package com.fedex.sad;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value= {"/", "/all"})
public class UserController {		

	int userIdInt = -1;
	
	private List<User> userList = Arrays.asList(
		new User (1, "First1 Last1", "f.last1@gmail.com"),
		new User (2, "First2 Last2", "f.last2@gmail.com"),
		new User (3, "First3 Last3", "f.last3@gmail.com"),
		new User (4, "First4 Last4", "f.last4@gmail.com"),
		new User (5, "First5 Last5", "f.last5@gmail.com"),
		new User (6, "First6 Last6", "f.last6@gmail.com"),
		new User (7, "First7 Last7", "f.last7@gmail.com")
	);
	
//	@GetMapping ("") 
//	public List<User> findAllUsers () {
//		return userList;
//	}
//	
//	@GetMapping("/{userId}")
//	public User findUser (@PathVariable int userId) {
//		return userList.stream().filter(b -> (b.getId()==userId)).findFirst().orElse(null);
//	}
	
	@GetMapping("")
	public String getAllUsers (Model mdl) {
		// Trying securityContextHolder to fetch the context
		//UserDetails uDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication();
		Principal pal = SecurityContextHolder.getContext().getAuthentication();
		
		if (pal instanceof UserDetails) {
			UserDetails uDetails = (UserDetails) pal;
			System.out.println("UserController ::: UserDetails ::: " + uDetails.toString());
		} else if (pal instanceof AnonymousAuthenticationToken) {
			System.out.println("UserController ::: AnonymousAuthenticationToken ::: " + pal.toString());			
		}
		
		mdl.addAttribute("users", userList);
		return "users";
	}
	
	@GetMapping("/{userId}")
	public String getUserById (Model mdl, @PathVariable String userId) {
		
		try {
			userIdInt = Integer.parseInt(userId);
			System.out.println("UserController ::: getUserById ::: UserID Entered = " + userIdInt);
		} catch (Exception e) {
			System.out.println("UserController ::: getUserById ::: EXCEPTION Occured ::: " + e);
			userIdInt = -1;
		}
		
		User usr = userList.stream().filter(b -> (b.getId()==userIdInt)).findFirst().orElse(null);
		
		System.out.println("User = "+ usr);
		
		if (usr != null) {			
			List<User> uList = Arrays.asList(usr);
			mdl.addAttribute("users", uList);
		} else {
			if (userIdInt == -1) {
				mdl.addAttribute("users", userList);
			} else {
				mdl.addAttribute("message", "No user is found with the User ID "+userId);
			}
		}
		return "users";
	}

}
