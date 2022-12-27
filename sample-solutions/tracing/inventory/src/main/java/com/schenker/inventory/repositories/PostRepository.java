package com.schenker.inventory.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.schenker.inventory.model.Post;

public interface PostRepository extends ListCrudRepository<Post,Integer> { }
