package com.schenker.api.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.schenker.api.model.Species;

public interface SpeciesService {

  @GetExchange("/inventory/species/{speciesId}")
  Species getSpecies(@PathVariable String speciesId);

  // more HTTP exchange methods...

}
