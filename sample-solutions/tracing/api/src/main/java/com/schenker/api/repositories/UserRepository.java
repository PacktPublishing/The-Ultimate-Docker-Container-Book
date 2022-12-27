package com.schenker.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.schenker.api.model.User;

public interface UserRepository extends CrudRepository<User, Integer> { }