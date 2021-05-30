package com.example.dinnerbell.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")

public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String restaurant_name;

  @Column(nullable = false, length = 15)
  private String phone_number;

  @Column(columnDefinition = "TEXT")
  private String hours;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  private String website_link;

  private String menu_link;

  private String elder_eats_link;

  @Column(nullable = false)
  private String street_address;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String zip_code;

  @Column(nullable = false, columnDefinition = "char(2) DEFAULT 'XX'")
  private String state;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "favorite_restaurants",
          joinColumns = {@JoinColumn(name = "restaurant_id")},
          inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  @JsonManagedReference
  private List<User> favorites;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "categories_restaurants",
          joinColumns = {@JoinColumn(name = "restaurant_id")},
          inverseJoinColumns = {@JoinColumn(name = "categories_id")}
  )
  @JsonManagedReference
  private List<Category> categories;

  public Restaurant() {
  }

  public Restaurant(long id, String restaurant_name, String phone_number, String hours, String description, String website_link, String menu_link, String elder_eats_link, String street_address, String city, String zip_code, String state, List<User> favorites, List<Category> categories) {
    this.id = id;
    this.restaurant_name = restaurant_name;
    this.phone_number = phone_number;
    this.hours = hours;
    this.description = description;
    this.website_link = website_link;
    this.menu_link = menu_link;
    this.elder_eats_link = elder_eats_link;
    this.street_address = street_address;
    this.city = city;
    this.zip_code = zip_code;
    this.state = state;
    this.favorites = favorites;
    this.categories = categories;
  }

  public Restaurant(String restaurant_name, String phone_number, String hours, String description, String website_link, String menu_link, String elder_eats_link, String street_address, String city, String zip_code, String state, List<User> favorites, List<Category> categories) {
    this.restaurant_name = restaurant_name;
    this.phone_number = phone_number;
    this.hours = hours;
    this.description = description;
    this.website_link = website_link;
    this.menu_link = menu_link;
    this.elder_eats_link = elder_eats_link;
    this.street_address = street_address;
    this.city = city;
    this.zip_code = zip_code;
    this.state = state;
    this.favorites = favorites;
    this.categories = categories;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRestaurant_name() {
    return restaurant_name;
  }

  public void setRestaurant_name(String restaurant_name) {
    this.restaurant_name = restaurant_name;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getWebsite_link() {
    return website_link;
  }

  public void setWebsite_link(String website_link) {
    this.website_link = website_link;
  }

  public String getMenu_link() {
    return menu_link;
  }

  public void setMenu_link(String menu_link) {
    this.menu_link = menu_link;
  }

  public String getElder_eats_link() {
    return elder_eats_link;
  }

  public void setElder_eats_link(String elder_eats_link) {
    this.elder_eats_link = elder_eats_link;
  }

  public String getStreet_address() {
    return street_address;
  }

  public void setStreet_address(String street_address) {
    this.street_address = street_address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip_code() {
    return zip_code;
  }

  public void setZip_code(String zip_code) {
    this.zip_code = zip_code;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<User> getFavorites() {
    return favorites;
  }

  public void setFavorites(List<User> favorites) {
    this.favorites = favorites;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
