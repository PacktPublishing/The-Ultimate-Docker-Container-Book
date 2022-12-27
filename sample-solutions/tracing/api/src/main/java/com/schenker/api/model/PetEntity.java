package com.schenker.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class PetEntity {
  @Id
  private String id;
  private String name;
  private String uri;
  private String speciesId;

  public PetEntity() {
  }
  
}
