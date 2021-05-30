
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

     @Value("/Users/jacob.k.valeriano/IdeaProjects/dinnerbell/target/classes/static/uploads/")
     private String uploadPath;

     public ReviewController(RestaurantRepo restaurantsdao, CategoryRepo categoriesdao, UserRepo usersdao, ReviewRepo reviewDao, ImageRepo imageDao) {
         this.restaurantsdao = restaurantsdao;
         this.categoriesdao = categoriesdao;
         this.usersdao = usersdao;
         this.reviewDao = reviewDao;
         this.imageDao = imageDao;
     }

     @GetMapping("/review/{id}")
     public String reviewPage(Model model, @PathVariable("id") long id) {
         Restaurant restaurant = restaurantsdao.getOne(id);
         model.addAttribute("restaurant", restaurant);
         return "post/review";
     }

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
         List<Image> reviewImages = new ArrayList<>();
         reviewImages.add(image);
         review.setImages(reviewImages);
         review.setRestaurant(restaurant);
         review.setContent(content);
         review.setUser(currentUser);
         reviewDao.save(review);

         return "redirect:/restaurant/review/{id}";
     }


     @GetMapping("/restaurant/review/{id}")
     public String viewReview(Model model, @PathVariable("id") long id) {
         Restaurant restaurant = restaurantsdao.getOne(id);
         List<Review> reviews = reviewDao.findAll();
         List<Image> images = imageDao.findAll();
         model.addAttribute("restaurant", restaurant);
         model.addAttribute("images", images);
         model.addAttribute("reviews", reviews);
         return "post/reviewPage";
     }

//     @GetMapping("/review/update/{id}")
//     public String updateReview(Model model, @PathVariable("id") long id){
//       Restaurant restaurant = restaurantsdao.getOne(id);
//       Review review = new Review();
//       model.addAttribute("review", reviewDao.getOne(review.getId()));
//       model.addAttribute("restaurant", restaurant);
//       return "post/updateReview";
//     }
//
//     @PostMapping("/review/update")
//     public String updateReviewResults(@RequestParam(name = "file")MultipartFile uploadedFile,
//                                       Model model,
//                                       @RequestParam(name = "content") String content,
//                                       @ModelAttribute Restaurant restaurant,
//                                       @ModelAttribute Review review){
//       Review reviewUpdate = reviewDao.getOne(review.getId());
//       Image image = new Image();
//       if(!uploadedFile.getOriginalFilename().isEmpty()){
//         String filename = uploadedFile.getOriginalFilename().replace(" ","_").toLowerCase();
//         String filepath = Paths.get(uploadPath,filename).toString();
//         File destinationFile = new File(filepath);
//         try {
//           uploadedFile.transferTo(destinationFile);
//           model.addAttribute("message","File successfully uploaded");
//         } catch (IOException e) {
//           e.printStackTrace();
//           model.addAttribute("message","Oops! Something went wrong!" + e);
//         }
//         image.setUrl(filename);
//       }
//       image.setRestaurant(restaurant);
//       List<Image> reviewImages = imageDao.findAll();
////       imageDao.save(image);
//
//       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//       User currentUser = usersdao.getOne(user.getId());
//
//       reviewImages.add(image);
//
////       reviewUpdate.setImages(reviewImages);
//
//       review.setImages(reviewImages);
//       review.setContent(reviewUpdate.getContent());
//       reviewUpdate.setUser(currentUser);
//       reviewDao.save(reviewUpdate);
//
//
//
//       return "redirect:/restaurant";
//     }

 }

