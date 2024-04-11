package com.akash.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.blogApp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByname(String name);
}
