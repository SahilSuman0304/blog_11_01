package com.fmg.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer commentId;
	    private String comment;
	    
	    @ManyToOne
	    @JoinColumn(name = "blogId")
	    private BlogPost blogPost;
}
