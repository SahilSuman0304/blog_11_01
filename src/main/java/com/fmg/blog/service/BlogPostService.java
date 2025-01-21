package com.fmg.blog.service;

import java.util.List;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.entities.BlogPost;

public interface BlogPostService {
	public BlogPostDTO createObject(BlogPostDTO blogPostDTO);

	public BlogPostDTO findByBlogPostId(Integer id);

	List<BlogPostDTO> getAllBlogPost();

	BlogPostDTO updateBlogPost(BlogPostDTO blogPostDTO);

	public BlogPost findBlogPostId(Integer blogPostId);

	public void delete(BlogPost blogPost);

	public BlogPostDTO deletePostById(Integer postId);

	BlogPostDTO findByBlogID(Integer Id);

	
}
