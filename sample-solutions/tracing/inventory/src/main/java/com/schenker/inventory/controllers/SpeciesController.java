package com.schenker.inventory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.schenker.inventory.model.Species;
import com.schenker.inventory.repositories.SpeciesRepository;

@RestController
@RequestMapping("/inventory/species")
public class SpeciesController {
  private final SpeciesRepository speciesRepository;

  public SpeciesController(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  @GetMapping
  public Iterable<Species> index() {
    return speciesRepository.findAll();
  } 

  @GetMapping("{id}")
  public Species GetById(@PathVariable String id) {
    var species = speciesRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such species found"));
    return species;
  }
}
