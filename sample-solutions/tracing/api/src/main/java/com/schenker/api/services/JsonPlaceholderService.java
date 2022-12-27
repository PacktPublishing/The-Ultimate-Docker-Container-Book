package com.schenker.api.services;

import org.springframework.web.service.annotation.GetExchange;

import com.schenker.api.model.Album;
import com.schenker.api.model.User;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("/users")
    List<User> loadUsers();

    @GetExchange("/albums")
    List<Album> loadAlbums();
}