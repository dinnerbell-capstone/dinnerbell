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
}
