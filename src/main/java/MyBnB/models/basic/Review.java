package MyBnB.models.basic;

public class Review {
  public enum Field {
    ID("id"),
    COMMENTS("comments"),
    RATING("rating"),
    BOOKING_ID("bookingID"),
    LISTING_ID("listingID"),
    REVIEWER_ID("reviewerID"),
    REVIEWEE_ID("revieweeID");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }
  private int id;
  private String comments;
  private int Rating;

  private int bookingID;
  private int listingID;
  private int reviewerID;
  private int revieweeID;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public int getRating() {
    return Rating;
  }

  public void setRating(int rating) {
    Rating = rating;
  }

  public int getBookingID() {
    return bookingID;
  }

  public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
  }

  public int getListingID() {
    return listingID;
  }

  public void setListingID(int listingID) {
    this.listingID = listingID;
  }

  public int getReviewerID() {
    return reviewerID;
  }

  public void setReviewerID(int reviewerID) {
    this.reviewerID = reviewerID;
  }

  public int getRevieweeID() {
    return revieweeID;
  }

  public void setRevieweeID(int revieweeID) {
    this.revieweeID = revieweeID;
  }

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", comments='" + comments + '\'' +
        ", Rating=" + Rating +
        ", bookingID=" + bookingID +
        ", listingID=" + listingID +
        ", reviewerID=" + reviewerID +
        ", revieweeID=" + revieweeID +
        '}';
  }
}
