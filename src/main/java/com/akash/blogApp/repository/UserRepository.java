package com.akash.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.blogApp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
