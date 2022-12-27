package com.schenker.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schenker.api.exceptions.PetNotFoundException;
import com.schenker.api.model.Pet;
import com.schenker.api.model.PetEntity;
import com.schenker.api.model.Species;
import com.schenker.api.repositories.PetEntityRepository;
import com.schenker.api.services.SpeciesService;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@RestController
@RequestMapping("/pets")
public class PetController {
  private final SpeciesService speciesService;
  private final ObservationRegistry observationRegistry;
  private final PetEntityRepository repository;

  public PetController(PetEntityRepository repository, SpeciesService client, ObservationRegistry observationRegistry){
    this.repository = repository;
    this.speciesService = client;
    this.observationRegistry = observationRegistry;
  }

	@GetMapping
	public Iterable<PetEntity> index(){
		return repository.findAll();
	}

  @GetMapping("{id}")
  public Pet getById(@PathVariable String id){
    var pet = repository.findById(id)
      .orElseThrow(() -> new PetNotFoundException(id));
      
    Species species = Observation
      .createNotStarted("json-place-holder.load-posts", observationRegistry)
      .observe(() -> speciesService.getSpecies(pet.getSpeciesId()));
    
      return new Pet(pet.getId(), pet.getName(), pet.getUri(), species);
  }

}



