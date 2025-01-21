package com.fmg.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmg.blog.dto.BlogPostDTO;
import com.fmg.blog.dto.CommentDTO;
import com.fmg.blog.service.CommentService;


@RestController
@RequestMapping("/api/posts")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommentController {

	@Autowired
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId) {

		if (postId.equals(commentDTO.getBlogPostId())) {
			CommentDTO createComment = commentService.createComment(commentDTO);
			return new ResponseEntity(createComment, HttpStatus.CREATED);
		} else {
			return new ResponseEntity("Post Id Not Match", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{postId}/comments")
	public ResponseEntity<CommentDTO> getCommentsBypostId(@PathVariable Integer postId) {
		List<CommentDTO> commentDTO = commentService.findCommentByBlogPostId(postId);

		return new ResponseEntity(commentDTO, HttpStatus.OK);

	}

	@GetMapping("/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentsByPostIdAndCommentId(@PathVariable Integer postId,
			@PathVariable Integer id) {
		List<CommentDTO> commentDTO = commentService.findCommentByBlogPostIdAndCommentId(postId, id);

		return new ResponseEntity(commentDTO, HttpStatus.OK);

	}

	@PutMapping("/{postId}/comments/{id}")
	public ResponseEntity<BlogPostDTO> updateBlogPost(@PathVariable Integer postId,
			@PathVariable("id") Integer commentId, @RequestBody CommentDTO commentDTO) {

		if (postId.equals(commentDTO.getBlogPostId()) && commentId.equals(commentDTO.getCommentId())) {
			CommentDTO getById = commentService.updateComments(commentDTO, postId, commentId);

			return new ResponseEntity(getById, HttpStatus.OK);
		} else {
			return new ResponseEntity("Post Id Not Match", HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(
	    @PathVariable Integer postId,
	    @PathVariable Integer id) {
	        commentService.deleteComment(postId, id);
	       // System.out.println("Comment Deleted successfully");
	        return new ResponseEntity("Deleted Successfully",HttpStatus.OK);  
	    
	}

}