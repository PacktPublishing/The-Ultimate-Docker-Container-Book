package com.schenker.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Album {
  @Id private Integer id;
  private Integer userId;
  private String title;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
}
