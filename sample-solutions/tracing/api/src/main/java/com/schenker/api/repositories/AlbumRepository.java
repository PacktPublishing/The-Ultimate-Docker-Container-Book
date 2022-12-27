package com.schenker.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.schenker.api.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Integer> { }