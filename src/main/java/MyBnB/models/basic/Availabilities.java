package MyBnB.models.basic;

import java.time.LocalDate;
import java.util.Objects;
import net.bytebuddy.asm.Advice;

public class Availabilities {
  public enum Field {
    PRICE_PER_NIGHT("pricePerNight"),
    END_DATE("endDate"),
    START_DATE("startDate"),
    LISTING_ID("listingID");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }
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
