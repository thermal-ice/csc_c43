package MyBnB.controller.requestbodies;

public class AddReviewBody {
  private int reviewerID;
  private int bookingID;
  private int rating;
  private String comments;

  public int getReviewerID() {
    return reviewerID;
  }

  public void setReviewerID(int reviewerID) {
    this.reviewerID = reviewerID;
  }

  public int getBookingID() {
    return bookingID;
  }

  public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "AddReviewBody{" +
        "reviewerID=" + reviewerID +
        ", bookingID=" + bookingID +
        ", rating=" + rating +
        ", comments='" + comments + '\'' +
        '}';
  }
}
