package com.fmg.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fmg.blog.entities.BlogPost;
import com.fmg.blog.entities.Comments;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Integer>{
	List<Comments> findByblogPost(BlogPost blogPostId);

	List<Comments> findByblogPostAndCommentId(BlogPost blogPost, Integer commentId);
    
	@Modifying
	@Transactional
	@Query("DELETE FROM Comments c WHERE c.blogPost.BlogId = :blogPostId AND c.commentId = :commentId")
	void deleteByBlogpostIdAndCommentId(@Param("blogPostId") Integer blogPostId, @Param("commentId") Integer commentId);
}
