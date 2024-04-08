package com.akash.blogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.blogApp.entity.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
	
}