package com.example.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.models.Species;
import com.example.library.repositories.SpeciesRepository;

@RestController
public class SpeciesController {
  private final SpeciesRepository repository;

  public SpeciesController(SpeciesRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/species")
  public Iterable<Species> index() {
    return repository.findAll();
  }

  @GetMapping("/species/{id}")
  public ResponseEntity<Species> getById(@PathVariable("id") Integer id) {
    var species = repository.findById(id);
    return species
      .map(r -> ResponseEntity.ok().body(r))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/species")
  public Species add(@RequestBody Species species) {
    return repository.save(species);
  }

  @PutMapping("/species/{id}")
  public ResponseEntity<Species> update(@PathVariable("id") Integer id, @RequestBody Species updatedSpecies) {
    var race = repository.findById(id);
    if(!race.isPresent())
      return ResponseEntity.notFound().build();
    
    updatedSpecies.setId(id);
    repository.save(updatedSpecies);
    return ResponseEntity.ok().body(updatedSpecies);
  }
}