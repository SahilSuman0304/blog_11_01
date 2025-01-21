package com.fmg.blog.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "blog_post")
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="blog_id")
	private Integer blogId;
//	@Column(name="blog_title")
	private String title;

	private String description;

	private String content;

	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Comments> comments;

	
	
	
}
