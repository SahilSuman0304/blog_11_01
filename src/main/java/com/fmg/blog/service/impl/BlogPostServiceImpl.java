package com.fmg.blog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.entities.BlogPost;
import com.fmg.blog.exception.NoResourceFound;
import com.fmg.blog.repo.BlogPostRepo;
import com.fmg.blog.service.BlogPostService;
@Service
public class BlogPostServiceImpl implements BlogPostService{
	
	
	private  BlogPostRepo  blogPostRepo;
	
	@Autowired
	public BlogPostServiceImpl(BlogPostRepo blogPostRepo) {
		super();
		this.blogPostRepo = blogPostRepo;
	}

	@Override
	public BlogPostDTO createObject(BlogPostDTO blogpost) {
		BlogPost save = blogPostRepo.save(mapDtoToEntity(blogpost));
		return mapEntityToDto(save);
	}
	
	private BlogPost mapDtoToEntity(BlogPostDTO blogPostDTO)
	{
		BlogPost blogPost= new BlogPost();
		blogPost.setContent(blogPostDTO.getContent());
		blogPost.setDescription(blogPostDTO.getDescription());
		blogPost.setTitle(blogPostDTO.getTitle());
		return blogPost;

	}
	private BlogPostDTO mapEntityToDto(BlogPost blogPost)
	{
		BlogPostDTO blogPostDto= new BlogPostDTO();
		blogPostDto.setContent(blogPost.getContent());
		blogPostDto.setDescription(blogPost.getDescription());
		blogPostDto.setTitle(blogPost.getTitle());
		blogPostDto.setBlogID(blogPost.getBlogId());
		return blogPostDto;

	}

	@Override
	public BlogPostDTO findByBlogID(Integer Id) {
		// TODO Auto-generated method stub
		Optional <BlogPost> findId = blogPostRepo.findById(Id);
		BlogPost post = findId.orElseThrow(() -> new NoResourceFound("BlogPost", "Id", Id));
		return mapEntityToDto(post);
	}

	
}
