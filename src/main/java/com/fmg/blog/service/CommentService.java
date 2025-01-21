package com.fmg.blog.service;

import java.util.List;

import com.fmg.blog.dto.CommentDTO;

public interface CommentService {
	CommentDTO createComment(CommentDTO commentDTO);

	List<CommentDTO> findCommentByBlogPostId(Integer postId);

	List<CommentDTO> findCommentByBlogPostIdAndCommentId(Integer postId, Integer commentId);

	CommentDTO updateComments(CommentDTO commentDTO, Integer postId, Integer commentId);

	void deleteComment(Integer postId, Integer commentId);
}
