package MyBnB.repository.implementations;

import MyBnB.controller.requestbodies.AddReviewBody;
import MyBnB.models.basic.Review;
import MyBnB.repository.interfaces.IReviewRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository implements IReviewRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getAllReviews() {// TODO
        return jdbcTemplate.query("Select * From Review;", new BeanPropertyRowMapper<>(Review.class));
    }

    @Override
    public Review getReviewByID(int reviewID) {// TODO
        return null;
    }

    @Override
    public List<Review> getAllReviewsForUser(int revieweeID) {// TODO
        return null;
    }

    @Override
    public List<Review> getAllReviewsFromUser(int reviewerID) {// TODO
        return null;
    }

    @Override
    public List<Review> getAllReviewsForBooking(int bookingID) {// TODO
        return null;
    }

    @Override
    public List<Review> getAllReviewsForListing(int listingID) {// TODO
        return null;
    }

    @Override
    public String addReview(AddReviewBody newReview) {
        return jdbcTemplate.queryForObject("CALL sp_addReview(?,?,?,?);", String.class,
            newReview.getBookingID(),
            newReview.getReviewerID(),
            newReview.getRating(),
            newReview.getComments());
    }

    @Override
    public void deleteReview(Integer reviewID){
      jdbcTemplate.update("DELETE FROM Review WHERE id=?",reviewID);
    }
}
