package com.schenker.inventory.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.schenker.inventory.model.Species;

public interface SpeciesRepository extends ListCrudRepository<Species,String> { }