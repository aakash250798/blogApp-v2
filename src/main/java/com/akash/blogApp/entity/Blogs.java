package com.akash.blogApp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Blogs {
	@Id
	@GeneratedValue
	int id;
	String heading;
	@Column(length = 10000)
	String story;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="users_id", referencedColumnName = "id")
	private String userName;
	private String time;
	@OneToMany(cascade = CascadeType.ALL)
//	@JsonManagedReference
	List<Comments>comments;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id",nullable=false)
	private User user;
	
}