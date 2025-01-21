package com.fmg.blog.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.entities.BlogPost;

public interface BlogPostService {
	public BlogPostDTO createObject(BlogPostDTO blogPostDTO);

	List<BlogPostDTO> getAllBlogPost(PageRequest of);

	BlogPostDTO updateBlogPost(BlogPostDTO blogPostDTO);

	public BlogPost findBlogPostId(Integer blogPostId);

	public void delete(BlogPost blogPost);

	public BlogPostDTO deletePostById(Integer postId);

	BlogPostDTO findByBlogID(Integer Id);

	
}
