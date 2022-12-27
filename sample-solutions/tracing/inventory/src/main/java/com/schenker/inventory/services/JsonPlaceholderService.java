package com.schenker.inventory.services;

import org.springframework.web.service.annotation.GetExchange;

import com.schenker.inventory.model.Post;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("/posts")
    List<Post> loadPosts();

}