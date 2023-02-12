package com.example.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.models.Race;
import com.example.library.repositories.RaceRepository;

@RestController
public class RacesController {
  private final RaceRepository repository;

  public RacesController(RaceRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/races")
  public Iterable<Race> index() {
    return repository.findAll();
  }

  @GetMapping("/races/{id}")
  public ResponseEntity<Race> getById(@PathVariable("id") Integer id) {
    var race = repository.findById(id);
    return race
      .map(r -> ResponseEntity.ok().body(r))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/species/{speciesId}/races")
  public Iterable<Race> getBySpecies(@PathVariable("speciesId") Integer speciesId) {
    return repository.findBySpeciesId(speciesId);
  }

  @PostMapping("/races")
  public Race add(@RequestBody Race race) {
    return repository.save(race);
  }

  @PutMapping("/races/{id}")
  public ResponseEntity<Race> update(@PathVariable("id") Integer id, @RequestBody Race updatedRace) {
    var race = repository.findById(id);
    if(!race.isPresent())
      return ResponseEntity.notFound().build();
    
    updatedRace.setId(id);
    repository.save(updatedRace);
    return ResponseEntity.ok().body(updatedRace);
  }
}