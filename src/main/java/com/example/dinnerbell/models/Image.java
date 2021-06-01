package com.example.dinnerbell.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

  @JsonBackReference
  @ManyToOne
  private Restaurant restaurant;

  @ManyToOne
  @JsonBackReference
  private Review review;

  public Image() {
  }

  public Image(long id, String url, Restaurant restaurant) {
    this.id = id;
    this.url = url;
    this.restaurant = restaurant;
  }

  public Image(String url, Restaurant restaurant) {
    this.url = url;
    this.restaurant = restaurant;
  }

  public Image(long id, String url, Restaurant restaurant, Review review) {
    this.id = id;
    this.url = url;
    this.restaurant = restaurant;
    this.review = review;
  }

  public Image(String url, Restaurant restaurant, Review review) {
    this.url = url;
    this.restaurant = restaurant;
    this.review = review;
  }

  public Review getReview() {
    return review;
  }

  public void setReview(Review review) {
    this.review = review;
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

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }
}
