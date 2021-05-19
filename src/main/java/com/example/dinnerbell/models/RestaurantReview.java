package com.example.dinnerbell.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "restaurant_reviews")
public class RestaurantReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne
  private User user;

  @OneToOne
  private FavoriteRestaurant restaurant;

  @Column(columnDefinition = "TEXT NOT NULL")
  private String content;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "review_images",
    joinColumns = {@JoinColumn(name = "restaurant_review_id")},
    inverseJoinColumns = {@JoinColumn(name = "images_id")}
  )
  private List<Image> images;

  public RestaurantReview() {
  }

  public RestaurantReview(long id, User user, FavoriteRestaurant restaurant, String content, Timestamp createdAt, List<Image> images) {
    this.id = id;
    this.user = user;
    this.restaurant = restaurant;
    this.content = content;
    this.createdAt = createdAt;
    this.images = images;
  }

  public RestaurantReview(User user, FavoriteRestaurant restaurant, String content, Timestamp createdAt) {
    this.user = user;
    this.restaurant = restaurant;
    this.content = content;
    this.createdAt = createdAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public FavoriteRestaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(FavoriteRestaurant restaurant) {
    this.restaurant = restaurant;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
