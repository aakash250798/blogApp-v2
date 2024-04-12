package com.akash.blogApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
	
	private UserDetailsService userDetailsService;
	
	public WebSpringSecurity (UserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}
//	@Bean
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().anyRequest("/images/**");
//	    web.ignoring().antMatchers("/images/**", "/js/**", "/css/**", "/webjars/**").;
//	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
        // other configs...
    
     
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize)->authorize
						.requestMatchers(new AntPathRequestMatcher("/showBlog/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/getBlog"))
						.hasAnyRole("ADMIN","GUEST")
						.requestMatchers(new AntPathRequestMatcher("/saveBlog"))
						.hasAnyRole("ADMIN","GUEST")
						.requestMatchers(new AntPathRequestMatcher("/showNewBlogForm"))
						.hasAnyRole("ADMIN","GUEST")
						.requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/newBlog/**")).permitAll()
						.anyRequest().authenticated()
					)
				
				.formLogin(form->form.loginPage("/login")
						.defaultSuccessUrl("/getBlog")
						.loginProcessingUrl("/login")
						.permitAll()
						).logout (logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll()
						);
				  
				
		return http.build();
	}
				
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).
		passwordEncoder(passwordEncoder());
	}
		
		
			
	
}
