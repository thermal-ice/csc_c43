package MyBnB.repository.implementations;

import MyBnB.models.basic.Review;
import MyBnB.repository.interfaces.IReviewRepository;

import java.util.List;

public class ReviewRepository implements IReviewRepository {

    @Override
    public List<Review> getAllReviews() {// TODO
        return null;
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
    public void addReview(Review newReview) {
        // TODO
    }
}
