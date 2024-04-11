package com.akash.blogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akash.blogApp.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {

	
	/*@Query("SELECT b from Blogs b where "+" b.heading LIKE CONCAT('%',:query,'%') OR "
	+" b.story LIKE CONCAT('%', :query, '%')")
	List<Blogs>searchPost(String query);*/
	
	
	@Query(value ="select * from blogs b where b.user_id =:id", nativeQuery=true)
	List<Blogs> findAllBlogs(Integer id);
}