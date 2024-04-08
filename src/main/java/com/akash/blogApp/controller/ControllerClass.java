
package com.akash.blogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.akash.blogApp.entity.Blogs;
import com.akash.blogApp.entity.Comments;
import com.akash.blogApp.service.ServiceClass;

@Controller
public class ControllerClass {
	@Autowired
	ServiceClass service;
	@GetMapping("/")
	public String getAllBlogs(Model model) {
		return service.getAllBlogs(model);
		
	}
	@GetMapping("/showNewBlogForm")
	 public String showNewBlogForm(Model model) {
	     // create model attribute to bind form data
		return service.showNewBlogForm(model);
	     
	 }
	@PostMapping("/saveBlog")
	public String saveBlogs(@ModelAttribute("blog") Blogs blog) {
		return service.saveBlogs(blog);
		
		
	}
	@PostMapping("/saveComment/{id}")
	public String addComment(@PathVariable int id ,@ModelAttribute("comment") Comments comment, Model model) {
		return service.addComment(id,comment,model);
		
		
	}
//	@PostMapping("/addBlogs/{uid}")
//	public Blogs addBlogs(@RequestBody Blogs blogs ,@PathVariable Integer uid) {
//		blogs.setUsers(userRepo.findById(uid).get());
//		return blogRepo.save(blogs);
//	}
	
	
	@GetMapping("/deleteBlog/{bid}")
	public String deleteBlogs(@PathVariable int bid) {
		return service.deleteBlogs(bid);
		
		
	}
	@GetMapping("/showBlog/{id}")
	public String showBlog( @PathVariable int id, Model model) {
		return service.showBlog(id,model);
	}
	
}