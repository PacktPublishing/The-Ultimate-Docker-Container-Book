package com.schenker.inventory.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schenker.inventory.exceptions.PostNotFoundException;
import com.schenker.inventory.model.Post;
import com.schenker.inventory.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostRepository postRepository;

    public PostsController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Integer id) {
        return postRepository
          .findById(id)
          .orElseThrow(() -> new PostNotFoundException(id));
    }

}