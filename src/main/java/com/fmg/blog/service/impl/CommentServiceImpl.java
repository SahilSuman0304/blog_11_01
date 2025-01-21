package com.fmg.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmg.blog.dto.CommentDTO;
import com.fmg.blog.entities.BlogPost;
import com.fmg.blog.entities.Comments;
import com.fmg.blog.exception.NoResourceFound;
import com.fmg.blog.repo.CommentRepo;
import com.fmg.blog.service.BlogPostService;
import com.fmg.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	

	@Autowired
	private CommentRepo commentRepository;

	@Autowired
	private BlogPostService blogPostService;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO) {
		Comments save = commentRepository.save(mapDtoToEntity(commentDTO));
		return mapEntityToDto(save);
	}
	private CommentDTO mapEntityToDto(Comments comments) {
		CommentDTO commentDTO=new CommentDTO();
		commentDTO.setBlogPostId(comments.getBlogPost().getBlogId());
		commentDTO.setComment(comments.getComment());
		commentDTO.setCommentId(comments.getCommentId());
		return commentDTO;
	}

	private Comments mapDtoToEntity(CommentDTO commentDTO) {
		BlogPost blogPost = blogPostService.findBlogPostId(commentDTO.getBlogPostId());

		Comments comments = new Comments();
		comments.setComment(commentDTO.getComment());
		comments.setBlogPost(blogPost);
		comments.setCommentId(commentDTO.getCommentId());
		return comments;
	}

	@Override
	public List<CommentDTO> findCommentByBlogPostId(Integer postId) {
		BlogPost blogPost = blogPostService.findBlogPostId(postId);
		//Optional<Comments> blogPostId = blogPostService.findById();
		List<Comments> comments=commentRepository.findByblogPost(blogPost);
		return comments.stream().map(comment -> mapEntityToDto(comment)).toList();
	}

	@Override
	public List<CommentDTO> findCommentByBlogPostIdAndCommentId(Integer postId, Integer commentId) {
		BlogPost blogPost = blogPostService.findBlogPostId(postId);
		List<Comments> comments=commentRepository.findByblogPostAndCommentId(blogPost,commentId);
		return comments.stream().map(comment -> mapEntityToDto(comment)).toList();
	}

	@Override
	public CommentDTO updateComments(CommentDTO commentDTO, Integer postId, Integer commentId) {
		BlogPost blogPost = blogPostService.findBlogPostId(postId);
		Optional<Comments> comments= commentRepository.findById(commentId);
		
		Comments update=null;
		if(blogPost!=null && comments.isPresent())
		{
			update=commentRepository.save(mapDtoToEntity(commentDTO));
		}else {
			throw new NoResourceFound("BlogPost", "Id", commentDTO.getCommentId());
		}
		return mapEntityToDto(update);
	}

	@Override
	public void deleteComment(Integer postId, Integer commentId) {
		 BlogPost blogPost = blogPostService.findBlogPostId(postId);
		    
		    // Ensure the comment exists
		    Comments comment = commentRepository.findById(commentId)
		        .orElseThrow(() -> new NoResourceFound("Comment", "Id", commentId));
		    
		    // Now delete the comment
		    commentRepository.deleteByBlogpostIdAndCommentId(blogPost.getBlogId(),comment.getCommentId());
	}

}
