package MyBnB.controller.requestbodies;

import java.time.LocalDate;

public class AddBookingBody {

  private int renterID;
  private int listingID;
  private LocalDate startDate;
  private LocalDate endDate;

  public int getRenterID() {
    return renterID;
  }

  public void setRenterID(int renterID) {
    this.renterID = renterID;
  }

  public int getListingID() {
    return listingID;
  }

  public void setListingID(int listingID) {
    this.listingID = listingID;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "AddBookingRequestBody{" +
        "renterID=" + renterID +
        ", listingID=" + listingID +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
