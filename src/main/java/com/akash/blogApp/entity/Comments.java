 package com.akash.blogApp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String statement;
	private String time;
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="users_id", referencedColumnName = "id")
//	@JsonBackReference
//	private Users users;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="blogs_id", referencedColumnName = "id")
//	@JsonBackReference
//	private Blogs blogs;
	
}