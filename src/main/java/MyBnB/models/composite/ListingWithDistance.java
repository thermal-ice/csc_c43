package MyBnB.models.composite;

import MyBnB.models.basic.Listing;

public class ListingWithDistance {
  private Listing listing;
  private double distanceToUser;

  public ListingWithDistance(Listing listing){
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


}
