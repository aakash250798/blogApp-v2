package com.akash.blogApp.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.akash.blogApp.entity.Role;
import com.akash.blogApp.entity.User;
import com.akash.blogApp.repository.RoleRepository;
import com.akash.blogApp.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
	@GetMapping("/signup")
	public String userSignUp(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "signup";
	}

	
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") User user,
			BindingResult result,
			Model model){
		User existingUser=userRepo.findByEmail(user.getEmail());
		if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) {
			//result.rejectValue("email",null,"User with this email already exists");
			System.out.println("odifuaodsf");
			result.rejectValue("email", null, "User already registered with this mail");
			return "signup";
		}
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			System.out.println("kddjsdv");
			return "signup";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role=roleRepo.findByname("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		
		userRepo.save(user);
		return "redirect:/signup?success";
	}
	

}
