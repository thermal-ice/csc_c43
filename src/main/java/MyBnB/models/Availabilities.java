package MyBnB.models;

import java.time.LocalDate;
import java.util.Objects;
import net.bytebuddy.asm.Advice;

public class Availabilities {
  private LocalDate startDate;
  private int listingID;
  private double pricePerNight;
  private LocalDate endDate;

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public int getListingID() {
    return listingID;
  }

  public void setListingID(int listingID) {
    this.listingID = listingID;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(double pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "Availabilities{" +
        "startDate=" + startDate +
        ", listingID=" + listingID +
        ", pricePerNight=" + pricePerNight +
        ", endDate=" + endDate +
        '}';
  }
}
