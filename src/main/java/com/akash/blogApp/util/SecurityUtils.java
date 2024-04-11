package com.akash.blogApp.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;



public final class SecurityUtils {
	public static User getCurrentUser() {
//		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
//			    SecurityContextHolder.getContext().getAuthentication();
//		User details = (User) auth.getDetails();
//		return details;
		
	    
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( principal instanceof User) {
			System.out.println("hello");
			return (User)principal;
		}
		System.out.println("hi");
		return null;
	}

}
