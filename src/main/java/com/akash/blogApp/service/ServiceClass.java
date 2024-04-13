package com.akash.blogApp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.akash.blogApp.entity.Blogs;
import com.akash.blogApp.entity.Comments;
import com.akash.blogApp.entity.User;
import com.akash.blogApp.repository.BlogRepository;
import com.akash.blogApp.repository.CommentRepository;
import com.akash.blogApp.repository.UserRepository;
import com.akash.blogApp.util.SecurityUtils;

@Service
public class ServiceClass {
	@Autowired
	BlogRepository blogRepo;
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	UserRepository userRepo;
	public String showBlog(int id, Model model) {
		model.addAttribute("blog",blogRepo.findById(id).get());
		return "blogPage";
		// TODO Auto-generated method stub
		
	}
	public String deleteBlogs(int bid) {
		blogRepo.deleteById(bid);
		return "redirect:/getBlog";
	}
	public String addComment(int id, Comments comment, Model model) {
		Blogs blog=blogRepo.findById(id).get();
				
		comment.setId(blog.getComments().size()+3);
		
		Comments c1=commentRepo.save(comment);
		List<Comments>l=blog.getComments();
		l.add(c1);
		blog.setComments(l);
		blogRepo.save(blog);
		
		model.addAttribute("blog",blog);
		return "redirect:/blogPage";
	}
	public String saveBlogs(Blogs blog) {
		String email=SecurityUtils.getCurrentUser().getUsername();
		User user=userRepo.findByEmail(email);
		if(user.getEmail()==null)
			System.out.println("hello world");
		LocalDateTime time =LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM, HH:mm a");
		String format=" "+time.format(myFormatObj)+" ";
		
		blog.setUser(user);
		blog.setTime(format);
		blogRepo.save(blog);
		return "redirect:/getBlog";
	}
	public String showNewBlogForm(Model model) {
		Blogs blog = new Blogs();
	    model.addAttribute("blog", blog);
	    return "new_blog";
	}
	public String getAllBlogs(Model model) {
		String email=SecurityUtils.getCurrentUser().getUsername();
		User user=userRepo.findByEmail(email);
		
		model.addAttribute("listBlogs",blogRepo.findAllBlogs(user.getId()));
		return "getBlog";
	}
}
