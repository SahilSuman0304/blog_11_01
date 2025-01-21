package com.fmg.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.entities.BlogPost;
import com.fmg.blog.exception.NoResourceFound;
import com.fmg.blog.repo.BlogPostRepo;
import com.fmg.blog.service.BlogPostService;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class BlogPostServiceImpl implements BlogPostService{
	
	@Autowired
	private  BlogPostRepo  blogPostRepo;
	
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
		blogPost.setBlogId(blogPostDTO.getBlogID());
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
	
	@Override
	public List<BlogPostDTO> getAllBlogPost(){
		List<BlogPost> findAll = blogPostRepo.findAll();
		// using stream
		 return findAll.stream().map(blogpost -> mapEntityToDto(blogpost)).toList();

		// using core java
//		List<BlogPostDTO> blogpostDtos = new ArrayList<>();
//		for (BlogPost blogPost : findAll) {
//			blogpostDtos.add(mapEntityToDto(blogPost));
//
//		}
//		return blogpostDtos;
	}
	
	@Override
	public BlogPostDTO updateBlogPost(BlogPostDTO blogpostdto) {
		Optional<BlogPost> findbyID = blogPostRepo.findByTitle(blogpostdto.getTitle());
		//findbyID.orElseThrow(() ->new NoResourceFound("BlogPost", "ID", blogpostdto.getBlogID() ));
		BlogPost update = null;
		if(findbyID.isPresent()) {
			update = blogPostRepo.save(mapDtoToEntity(blogpostdto));	
		}else {
			throw new NoResourceFound("BlogPost", "ID", blogpostdto.getBlogID());
		}
		
		return mapEntityToDto(update);
	}

	@Override
	public BlogPost findBlogPostId(Integer blogPostId) {
		Optional<BlogPost> findById = blogPostRepo.findById(blogPostId);
		BlogPost blogPost = findById.orElseThrow(() -> new NoResourceFound("BlogPost", "Id", blogPostId));
	return blogPost;
	}

	@Override
	public void delete(BlogPost blogPost) {
		blogPostRepo.delete(blogPost);
		
	}

	@Override
	public BlogPostDTO deletePostById(Integer id) {
		BlogPost blogPost = blogPostRepo.findById(id).orElseThrow(() -> new NoResourceFound("BlogPost", "Id", id));
		blogPostRepo.deleteById(id);
		return mapEntityToDto(blogPost);
	}

	
}
