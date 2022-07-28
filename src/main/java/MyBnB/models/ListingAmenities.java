package MyBnB.models;

public class ListingAmenities {

  private int listingID;
  private String amenity;

  public int getListingID() {
    return listingID;
  }

  public void setListingID(int listingID) {
    this.listingID = listingID;
  }

  public String getAmenity() {
    return amenity;
  }

  public void setAmenity(String amenity) {
    this.amenity = amenity;
  }

  @Override
  public String toString() {
    return "ListingAmenities{" +
        "listingID=" + listingID +
        ", amenity='" + amenity + '\'' +
        '}';
  }
}
