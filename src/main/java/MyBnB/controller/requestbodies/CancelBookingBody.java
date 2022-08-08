package MyBnB.controller.requestbodies;

public class CancelBookingBody {

  private int userID;
  private int bookingID;

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getBookingID() {
    return bookingID;
  }

  public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
  }

  @Override
  public String toString() {
    return "CancelBookingBody{" +
        "userID=" + userID +
        ", bookingID=" + bookingID +
        '}';
  }
}
