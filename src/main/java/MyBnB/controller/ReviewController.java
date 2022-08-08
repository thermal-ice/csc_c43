package MyBnB.controller;

import MyBnB.controller.requestbodies.AddReviewBody;
import MyBnB.models.basic.Renter;
import MyBnB.models.basic.Review;
import MyBnB.models.basic.User;
import MyBnB.repository.implementations.RenterRepository;
import MyBnB.repository.implementations.ReviewRepository;
import MyBnB.services.MostPopularNounPhrases;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/reviews")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

//    @Autowired
//    ReviewController reviewController;
//
//    @GetMapping("/getAllRenters")
//    public List<Renter> getAllRenters() {
//        return reviewController.getAllRenters();
//    }
//
//    // NOTE: assumes valid renter id for now
//    @GetMapping("/getRenter")
//    public @ResponseBody User getRenter(@RequestParam("id") int id) {
//        return reviewController.getRenter(id);
//    }
//
//    @PostMapping("/addRenter")
//    public void addRenter(@RequestBody Renter renter) {
//        reviewController.addRenter(renter);
//    }
//
//    @PostMapping("/deleteRenter")
//    public void deleteRenter(@RequestParam("id") int id) {
//        reviewController.deleteRenter(id);
//    }

    @GetMapping("/nounPhrases")
    public Map<Integer, Map<String,Integer>> getNounPhraseCountByListing(){

      List<Review> reviewList = reviewRepository.getAllReviews();
      return MostPopularNounPhrases.getMostPopularNounPhrases(reviewList);
    }

    @PostMapping(value={"/add","/modify"})
    public String addNewReview(@RequestBody AddReviewBody requestBody){
      return reviewRepository.addReview(requestBody);
    }

    @DeleteMapping("/delete")
    public void deleteReview(@RequestParam Integer reviewID){
      reviewRepository.deleteReview(reviewID);
    }
}