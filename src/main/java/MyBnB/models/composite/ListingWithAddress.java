package MyBnB.models.composite;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;

public class ListingWithAddress {

  private Listing listing;
  private Address address;

  public ListingWithAddress(Listing listing, Address address){
    this.listing = listing;
    this.address = address;
  }

  public Listing getListing() {
    return listing;
  }

  public void setListing(Listing listing) {
    this.listing = listing;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
