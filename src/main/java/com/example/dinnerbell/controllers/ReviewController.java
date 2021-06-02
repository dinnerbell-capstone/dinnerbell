
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

     @Value("${file_upload_path}")
     private String uploadPath;

     public ReviewController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao, ReviewRepo reviewDao, ImageRepo imageDao) {
         this.restaurantsdao = restaurantsdao;
         this.categoriesdao = categoriesdao;
         this.usersdao = usersdao;
         this.reviewDao = reviewDao;
         this.imageDao = imageDao;
     }

     @GetMapping("/review/{id}")
     public String reviewForm(Model model, @PathVariable("id") long id) {
         Restaurant restaurant = restaurantsdao.getOne(id);
         model.addAttribute("restaurant", restaurant);
         return "post/review";
     }

     @PostMapping("/review/{id}")
     public String reviewFormResults(
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
         Review review = new Review();
         List<Image> reviewImages = new ArrayList<>();
         image.setReview(review);
         reviewImages.add(image);
         review.setImages(reviewImages);
         review.setRestaurant(restaurant);
         review.setContent(content);
         review.setUser(currentUser);
         reviewDao.save(review);

         return "redirect:/reviews/byRestaurant/" + restaurant.getId();
     }





   @GetMapping("/reviews/byRestaurant/{id}")
     public String viewReview(Model model, @PathVariable("id") long id) {
     Restaurant restaurant = restaurantsdao.getOne(id);
     List<Review> reviewsForRestaurant = reviewDao.findAllByRestaurant(restaurant);
     User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     model.addAttribute("currentUser",currentUser);
     model.addAttribute("restaurant", restaurant);
     model.addAttribute("reviews",reviewsForRestaurant);
         return "post/reviewPage";
     }



     @GetMapping("/review/update/{id}")
     public String updateReview(Model model, @PathVariable("id") long id){

       Review review = reviewDao.getOne(id);
       model.addAttribute("review",review);

       return "post/updateReview";
     }

     @PostMapping("/review/update/{id}")
     public String updateReviewResults(@RequestParam(name = "file")MultipartFile uploadedFile, Model model, @ModelAttribute("review") Review review, @PathVariable("id") long id){
       Review reviewFromDB = reviewDao.getOne(id);
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
         image.setRestaurant(reviewFromDB.getRestaurant());
         imageDao.save(image);
         List<Image> updatedImages = new ArrayList<>();
         updatedImages.add(image);
         reviewFromDB.setImages(updatedImages);
       }

       reviewFromDB.setContent(review.getContent());

       reviewDao.save(reviewFromDB);
       return "redirect:/reviews/byRestaurant/" + reviewFromDB.getRestaurant().getId();
     }

   @GetMapping("/review/delete/{id}")
   public String deleteReview(@PathVariable("id") long id){
       Review review = reviewDao.getOne(id);
     reviewDao.deleteById(id);
     return "redirect:/reviews/byRestaurant/" + review.getRestaurant().getId();
   }

 }

