package com.fmg.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.entities.BlogPost;
import com.fmg.blog.service.BlogPostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/posts")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BlogPostController {
	@Autowired
	private BlogPostService blogPostService;
	
	@PostMapping("/save")
	public ResponseEntity<BlogPostDTO> createBlog(@RequestBody BlogPostDTO blogpost) {
		BlogPostDTO createBlogPost = blogPostService.createObject(blogpost);
		return new ResponseEntity<BlogPostDTO>(createBlogPost,HttpStatus.CREATED);
	}
	@GetMapping("/{postId}")
	public ResponseEntity<BlogPostDTO> findBlog(@PathVariable Integer postId) {
		BlogPost createBlogPost  =blogPostService.findBlogPostId(postId);
		return new ResponseEntity(createBlogPost,HttpStatus.CREATED);
	}
	@GetMapping("/findAll")
	public ResponseEntity<BlogPostDTO> getAllBlogPost(){
		List<BlogPostDTO> getById=blogPostService.getAllBlogPost();
		getById.forEach(s -> System.out.println(s));
		return new ResponseEntity(getById, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<BlogPostDTO> updateBlogPost(@RequestBody BlogPostDTO blogPostDTO){

		BlogPostDTO getById=blogPostService.updateBlogPost(blogPostDTO);
		
		return new ResponseEntity<BlogPostDTO>(getById, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable Integer postId) {
		blogPostService.deletePostById(postId);
		return new ResponseEntity("Data Deleted successully",HttpStatus.OK);
	}
	
	
}
