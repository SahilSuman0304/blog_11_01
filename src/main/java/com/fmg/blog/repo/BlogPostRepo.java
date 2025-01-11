package com.fmg.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fmg.blog.entities.BlogPost;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>{

}
