package com.example.dinnerbell.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String url;

  @OneToOne
  private FavoriteRestaurant restaurant;

  @ManyToMany(mappedBy = "images")
  private List<RestaurantReview> restaurantReviews;

  public Image() {
  }

  public Image(long id, String url, FavoriteRestaurant restaurant) {
    this.id = id;
    this.url = url;
    this.restaurant = restaurant;
  }

  public Image(String url, FavoriteRestaurant restaurant) {
    this.url = url;
    this.restaurant = restaurant;
  }

  public List<RestaurantReview> getRestaurantReviews() {
    return restaurantReviews;
  }

  public void setRestaurantReviews(List<RestaurantReview> restaurantReviews) {
    this.restaurantReviews = restaurantReviews;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public FavoriteRestaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(FavoriteRestaurant restaurant) {
    this.restaurant = restaurant;
  }
}
