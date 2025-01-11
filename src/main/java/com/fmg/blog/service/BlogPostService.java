package com.fmg.blog.service;

import com.fmg.blog.dto.BlogPostDTO;

public interface BlogPostService {
public BlogPostDTO createObject(BlogPostDTO blogpost);

public BlogPostDTO findByBlogID(Integer Id);
}
