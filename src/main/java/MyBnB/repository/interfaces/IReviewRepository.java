package MyBnB.repository.interfaces;

import MyBnB.controller.requestbodies.AddReviewBody;
import MyBnB.models.basic.Review;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository {
  public List<Review> getAllReviews();
  public Review getReviewByID(int reviewID);
  public List<Review> getAllReviewsForUser(int revieweeID);
  public List<Review> getAllReviewsFromUser(int reviewerID);
  public List<Review> getAllReviewsForBooking(int bookingID);
  public List<Review> getAllReviewsForListing(int listingID);
  public String addReview(AddReviewBody newReview);
  public void deleteReview(Integer reviewID);
}
