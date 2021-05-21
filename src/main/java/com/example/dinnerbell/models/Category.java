package com.example.dinnerbell.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "categories")
  @JsonBackReference
  private List<Restaurant> restaurants;

  public Category() {
  }

  public Category(long id, String name, List<Restaurant> restaurants) {
    this.id = id;
    this.name = name;
    this.restaurants = restaurants;
  }

  public Category(String name) {
    this.name = name;
  }

  public Category(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Restaurant> getRestaurants() {
    return restaurants;
  }

  public void setRestaurants(List<Restaurant> restaurants) {
    this.restaurants = restaurants;
  }
}
