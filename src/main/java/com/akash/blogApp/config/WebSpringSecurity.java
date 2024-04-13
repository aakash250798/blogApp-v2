package com.akash.blogApp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
	
	private UserDetailsService userDetailsService;
	
	public WebSpringSecurity (UserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}
	@Autowired
    private DataSource dataSource;

	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers("/images/**");
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
        // other configs...
    
     
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize)->authorize
						.requestMatchers(new AntPathRequestMatcher("https://free4kwallpapers.com/uploads/originals/2020/01/23/digital-art-k-wallpaper.jpg")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("https://images.hdqwalls.com/download/simple-minimal-mountains-landscape-4k-rc-1920x1080.jpg")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("https://picstatio.com/large/3eb697/simple-curves-abstract-4k.jpg")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("https://wallpapercave.com/wp/wp12807604.png")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("https://papers.co/wallpaper/papers.co-sk63-dark-red-blur-gradation-29-wallpaper.jpg")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("https://wallpapersmug.com/download/3840x2160/8ccbc0/yellow-back-pencils-underwatre.jpg")).permitAll()
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
				
				.formLogin(form->form.loginPage("/login"
						)
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
