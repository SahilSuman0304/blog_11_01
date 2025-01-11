package com.fmg.blog.dto;

import lombok.Data;

@Data
public class BlogPostDTO {
	
	private Integer blogID;
	private String title;
	private String description;
	private String content;
	
	
}
