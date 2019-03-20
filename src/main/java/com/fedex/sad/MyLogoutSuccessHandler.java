package com.fedex.sad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class MyLogoutSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements LogoutSuccessHandler {
	
	public MyLogoutSuccessHandler() {
		super();
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		final String refererUrl = request.getHeader("Referer");		
		System.out.println("MyLogoutSuccessHandler ::: onLogoutSuccess ::: REFERER" + refererUrl);
		
		// super.onLogoutSuccess (request, response, authentication);
		
	}

}
