package com.example.dinnerbell.controllers;

import com.example.dinnerbell.models.Image;
import com.example.dinnerbell.models.Restaurant;
import com.example.dinnerbell.models.Review;
import com.example.dinnerbell.models.User;
import com.example.dinnerbell.repositories.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewController {
  private final RestaurantRepo restaurantsdao;
  private final CategoryRepo categoriesdao;
  private final UserRepo usersdao;
  private final ReviewRepo reviewDao;
  private final ImageRepo imageDao;

  @Value("${file-upload-path}")
  private String uploadPath;

  public ReviewController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao, ReviewRepo reviewDao, ImageRepo imageDao) {
    this.restaurantsdao = restaurantsdao;
    this.categoriesdao = categoriesdao;
    this.usersdao = usersdao;
    this.reviewDao = reviewDao;
    this.imageDao = imageDao;
  }

//    @GetMapping("/review/{id}")
//    public String reviewPage(Model model, @PathVariable("id") long id) {
//      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      User currentUser = usersdao.getOne(user.getId());
//      Restaurant restaurant = restaurantsdao.getOne(id);
//      model.addAttribute("currentUser", currentUser);
//      model.addAttribute("restaurant", restaurant);
//      model.addAttribute("review", new Review());
//        return "post/review";
//    }
//
////  @GetMapping("/review")
////  public String showReviewForm(){
////    return "post/review";
////  }
//
//    private void saveFile(Image image, @RequestParam(name = "file")MultipartFile uploadedFile, Model model){
//      String filename = uploadedFile.getOriginalFilename();
//      String filepath = Paths.get(uploadPath,filename).toString();
//      File destinationFile = new File(filepath);
//      try {
//        uploadedFile.transferTo(destinationFile);
//        model.addAttribute("message","File successfully uploaded");
//      } catch (IOException e) {
//        e.printStackTrace();
//        model.addAttribute("message","Oops! Something went wrong!" + e);
//      }
//      image.setUrl(filename);
//    }
//
//
//
//
//    @PostMapping("/review/{id}")
//    public String PostReview(Image uploadedImage,
//                             @RequestParam(name = "file")MultipartFile uploadedFile,
//                             Model model,
//                             @ModelAttribute Review review,
//                             @PathVariable long id) {
//      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      User currentUser = usersdao.getOne(user.getId());
//      Restaurant restaurant = restaurantsdao.getOne(id);
////      model.addAttribute("restaurant",restaurant);
////      model.addAttribute("currentUser", currentUser);
//      // saves the uploaded image
//        saveFile(uploadedImage, uploadedFile, model);
//        Image savedImage = imageDao.save(uploadedImage);
//
//      // gets the id of the savedImage
//      Image image = imageDao.getOne(savedImage.getId());
//
//      // new instance of an array list
//      List<Image> reviewImages = new ArrayList<>();
//
//      // adds the saved image into the new instance of the array
//      reviewImages.add(savedImage);
//
//        //  these are for restaurant_review
//        review.setRestaurant(restaurant);
//        review.setUser(currentUser);
//
//      // sets images from the added savedImages
//      review.setImages(reviewImages);
//
//      // sets the restaurant id in images
//      savedImage.setRestaurant(restaurant);
//
//      image.setRestaurant(restaurant);
//      image.setId(uploadedImage.getId());
//
//
//
//        restaurantsdao.save(restaurant);
//        reviewDao.save(review);
//
//
//
//
//
//
//
//      return "redirect:/restaurant/details/{id}";
//    }




// testing
@GetMapping("/review/{id}")
public String reviewPage(Model model, @PathVariable("id") long id) {
//  User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//  User currentUser = usersdao.getOne(user.getId());
  Restaurant restaurant = restaurantsdao.getOne(id);
//  model.addAttribute("currentUser", currentUser);
  model.addAttribute("restaurant", restaurant);

  return "post/review";
}

//  @GetMapping("/review")
//  public String showReviewForm(){
//    return "post/review";
//  }

//  private void saveFile(Image uploadedImage, @RequestParam(name = "file")MultipartFile uploadedFile, Model model){
//
//
//    if(!uploadedFile.getOriginalFilename().isEmpty()){
//      String filename = uploadedFile.getOriginalFilename().replace(" ","_").toLowerCase();
//      String filepath = Paths.get(uploadPath,filename).toString();
//      File destinationFile = new File(filepath);
//         try {
//           uploadedFile.transferTo(destinationFile);
//           model.addAttribute("message","File successfully uploaded");
//         } catch (IOException e) {
//           e.printStackTrace();
//           model.addAttribute("message","Oops! Something went wrong!" + e);
//         }
//           uploadedImage.setUrl(filename);
//         }
//
//
//  }




  @PostMapping("/review/{id}")
  public String PostReview(
        @RequestParam(name = "file")MultipartFile uploadedFile,
                           Model model,
                           @RequestParam(name = "content") String content,
                           @ModelAttribute Restaurant restaurant) {
    Image image = new Image();
    if(!uploadedFile.getOriginalFilename().isEmpty()){
      String filename = uploadedFile.getOriginalFilename().replace(" ","_").toLowerCase();
      String filepath = Paths.get(uploadPath,filename).toString();
      File destinationFile = new File(filepath);
      try {
        uploadedFile.transferTo(destinationFile);
        model.addAttribute("message","File successfully uploaded");
      } catch (IOException e) {
        e.printStackTrace();
        model.addAttribute("message","Oops! Something went wrong!" + e);
      }
      image.setUrl(filename);
    }
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User currentUser = usersdao.getOne(user.getId());

    image.setRestaurant(restaurant);
    imageDao.save(image);

      Review review = new Review();
      // new instance of an array list
      List<Image> reviewImages = new ArrayList<>();
      reviewImages.add(image);
      review.setImages(reviewImages);
      // adds the saved image into the new instance of the array



        //  these are for restaurant_review
        review.setRestaurant(restaurant);
        review.setContent(content);
        review.setUser(currentUser);
      // sets images from the added savedImages
        reviewDao.save(review);

      // sets the restaurant id in images


//      image.setRestaurant(restaurant);
//      image.setId(uploadedImage.getId());











      return "redirect:/restaurant";
    }


}
