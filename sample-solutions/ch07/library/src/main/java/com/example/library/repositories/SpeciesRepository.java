package com.example.library.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.library.models.Species;

public interface SpeciesRepository extends CrudRepository<Species, Integer> {
  
}