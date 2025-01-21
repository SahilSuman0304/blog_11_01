package com.fmg.blog.dto;

import lombok.Data;


@Data
public class CommentDTO {
	private Integer commentId;
    private String comment;
    private Integer blogPostId;
}
