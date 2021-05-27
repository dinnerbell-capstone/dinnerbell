//package com.example.dinnerbell.models;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Entity
//@Table(name = "restaurant_reviews")
//public class Review {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private long id;
//
//  @OneToOne
//  private User user;
//
//  @OneToOne
//  private Restaurant restaurant;
//
//  @Column(columnDefinition = "TEXT NOT NULL")
//  private String content;
//
//  @Column(nullable = false)
//  @CreationTimestamp
//  private Timestamp createdAt;
//
//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//    name = "review_images",
//    joinColumns = {@JoinColumn(name = "restaurant_review_id")},
//    inverseJoinColumns = {@JoinColumn(name = "images_id")}
//  )
//  @JsonManagedReference
//  private List<Image> images;
//
//  public Review() {
//  }
//
//  public Review(long id, User user, Restaurant restaurant, String content, Timestamp createdAt, List<Image> images) {
//    this.id = id;
//    this.user = user;
//    this.restaurant = restaurant;
//    this.content = content;
//    this.createdAt = createdAt;
//    this.images = images;
//  }
//
//  public Review(User user, Restaurant restaurant, String content, Timestamp createdAt) {
//    this.user = user;
//    this.restaurant = restaurant;
//    this.content = content;
//    this.createdAt = createdAt;
//  }
//
//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }
//
//  public Restaurant getRestaurant() {
//    return restaurant;
//  }
//
//  public void setRestaurant(Restaurant restaurant) {
//    this.restaurant = restaurant;
//  }
//
//  public String getContent() {
//    return content;
//  }
//
//  public void setContent(String content) {
//    this.content = content;
//  }
//
//  public Timestamp getCreatedAt() {
//    return createdAt;
//  }
//
//  public void setCreatedAt(Timestamp createdAt) {
//    this.createdAt = createdAt;
//  }
//
//  public List<Image> getImages() {
//    return images;
//  }
//
//  public void setImages(List<Image> images) {
//    this.images = images;
//  }
//}
