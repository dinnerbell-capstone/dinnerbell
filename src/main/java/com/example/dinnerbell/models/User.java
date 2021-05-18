package com.example.dinnerbell.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 100, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private boolean isBusiness;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "favorite_restaurants",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "restaurant_id")}
  )
  private List<FavoriteRestaurant> restaurants;

  @OneToOne
  private FavoriteRestaurant restaurant;

  public User() {
  }

  public User(long id, String username, String email, String password, boolean isBusiness, List<FavoriteRestaurant> restaurants, FavoriteRestaurant restaurant) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isBusiness = isBusiness;
    this.restaurants = restaurants;
    this.restaurant = restaurant;
  }

  public User(String username, String email, String password, boolean isBusiness, List<FavoriteRestaurant> restaurants, FavoriteRestaurant restaurant) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.isBusiness = isBusiness;
    this.restaurants = restaurants;
    this.restaurant = restaurant;
  }

  public User(long id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public boolean getIsBusiness() {
    return isBusiness;
  }

  public void setIsBusiness(boolean business) {
    isBusiness = business;
  }

  public FavoriteRestaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(FavoriteRestaurant restaurant) {
    this.restaurant = restaurant;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<FavoriteRestaurant> getRestaurants() {
    return restaurants;
  }

  public void setRestaurants(List<FavoriteRestaurant> favorites) {
    this.restaurants = favorites;
  }
}
