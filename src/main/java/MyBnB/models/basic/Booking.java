package MyBnB.models.basic;

import java.time.LocalDate;

public class Booking {
  public enum Field {
    ID("id"),
    RENTER_ID("renterID"),
    HOST_ID("hostID"),
    AVAILABILITY_ID("availabilityID"),
    END_DATE("endDate"),
    START_DATE("startDate"),
    STATUS("status"),
    PRICE_PER_NIGHT("pricePerNight");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }

  private int id;
  private int renterID;
  private int hostID;
  private int listingID;
  private LocalDate startDate;
  private LocalDate endDate;
  private String status; //Turn into ENUM probably.

  public double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(double pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  private double pricePerNight;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRenterID() {
    return renterID;
  }

  public void setRenterID(int renterID) {
    this.renterID = renterID;
  }

  public int getHostID() {
    return hostID;
  }

  public void setHostID(int hostID) {
    this.hostID = hostID;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Booking{" +
        "id=" + id +
        ", renterID=" + renterID +
        ", hostID=" + hostID +
        ", listingID=" + listingID +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", status='" + status + '\'' +
        '}';
  }
}
