package com.example.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Race {
  @Id
  private Integer id;
  private Integer speciesId;
  private String name;
  private String description;
}
