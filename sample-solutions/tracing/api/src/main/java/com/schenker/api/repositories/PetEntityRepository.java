package com.schenker.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.schenker.api.model.PetEntity;

public interface PetEntityRepository extends CrudRepository<PetEntity, String> { }
