package com.example.dinnerbell.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class RestaurantCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "categories_restaurants",
    joinColumns = {@JoinColumn(name = "categories_id")},
    inverseJoinColumns = {@JoinColumn(name = "restaurant_id")}
  )
  private List<FavoriteRestaurant> restaurants;

  public RestaurantCategory() {
  }

  public RestaurantCategory(long id, String name, List<FavoriteRestaurant> restaurants) {
    this.id = id;
    this.name = name;
    this.restaurants = restaurants;
  }

  public RestaurantCategory(String name) {
    this.name = name;
  }

  public RestaurantCategory(long id, String name) {
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

  public List<FavoriteRestaurant> getRestaurants() {
    return restaurants;
  }

  public void setRestaurants(List<FavoriteRestaurant> restaurants) {
    this.restaurants = restaurants;
  }
}
