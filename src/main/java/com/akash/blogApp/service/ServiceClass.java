package com.akash.blogApp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.akash.blogApp.entity.Blogs;
import com.akash.blogApp.entity.Comments;
import com.akash.blogApp.repository.BlogRepository;
import com.akash.blogApp.repository.CommentRepository;

@Service
public class ServiceClass {
	@Autowired
	BlogRepository blogRepo;
	@Autowired
	CommentRepository commentRepo;
	public String showBlog(int id, Model model) {
		model.addAttribute("blog",blogRepo.findById(id).get());
		return "blogPage";
		// TODO Auto-generated method stub
		
	}
	public String deleteBlogs(int bid) {
		blogRepo.deleteById(bid);
		return "redirect:/";
	}
	public String addComment(int id, Comments comment, Model model) {
		Blogs blog=blogRepo.findById(id).get();
		comment.setId(blog.getComments().size()+1);
		LocalDateTime time =LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM, HH:mm a");
		String format="["+time.format(myFormatObj)+"]";
		
		
		comment.setTime(format);
		Comments c1=commentRepo.save(comment);
		System.out.println(c1);
		List<Comments>l=blog.getComments();
		l.add(c1);
		blog.setComments(l);
		blogRepo.save(blog);
		
		model.addAttribute("blog",blog);
		//model.addAttribute("comments",comment);
		return "blogPage";
	}
	public String saveBlogs(Blogs blog) {
		LocalDateTime time =LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM, HH:mm a");
		String format="["+time.format(myFormatObj)+"]";
		blog.setTime(format);
		blogRepo.save(blog);
		return "redirect:/";
	}
	public String showNewBlogForm(Model model) {
		Blogs blog = new Blogs();
	    model.addAttribute("blog", blog);
	    return "new_blog";
	}
	public String getAllBlogs(Model model) {
		model.addAttribute("listBlogs",blogRepo.findAll());
		return "getBlogs";
	}
}
