package com.akash.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.blogApp.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {

}