package MyBnB.models.composite;

import MyBnB.models.basic.Listing;

public class ListingWithDistanceAndPrice {
  private Listing listing;
  private double distanceToUser;
  private double price;

  public ListingWithDistanceAndPrice(Listing listing){
    this.listing = listing;
  }

  public void setListing(Listing listing) {
    this.listing = listing;
  }

  public Listing getListing() {
    return listing;
  }

  public double getDistanceToUser() {
    return distanceToUser;
  }

  public void setDistanceToUser(double distanceToUser) {
    this.distanceToUser = distanceToUser;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "ListingWithDistanceAndPrice{" +
        "listing=" + listing +
        ", distanceToUser=" + distanceToUser +
        ", price=" + price +
        '}';
  }
}
