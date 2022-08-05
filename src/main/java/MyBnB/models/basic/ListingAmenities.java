package MyBnB.models.basic;

public class ListingAmenities {
  public enum Field {
    LISTING_ID("listingID"),
    AMENITY("amenity");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }

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
