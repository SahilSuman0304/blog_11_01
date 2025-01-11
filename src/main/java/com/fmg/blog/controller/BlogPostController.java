package com.fmg.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.service.BlogPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
	@Autowired
	private BlogPostService blogPostService;
	
	@PostMapping
	public ResponseEntity<BlogPostDTO> createBlog(@RequestBody BlogPostDTO blogpost) {
		BlogPostDTO createBlogPost = blogPostService.createObject(blogpost);
		return new ResponseEntity<BlogPostDTO>(createBlogPost,HttpStatus.CREATED);
	}
	@GetMapping("/{postId}")
	public ResponseEntity<BlogPostDTO> findBlog(@RequestParam BlogPostDTO blogPostDTO) {
		BlogPostDTO createBlogPost  =blogPostService.createObject(blogPostDTO);
		return new ResponseEntity<BlogPostDTO>(createBlogPost,HttpStatus.CREATED);
	}
	
	
}
