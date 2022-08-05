package MyBnB.models.basic;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class Listing {

  //TODO figure this part out.
  // Nope it doesn't work. Just add the types to the description instead.
  // Also need to figure that part out

  public enum ListingType{
    APARTMENT("Apartment"),
    HOUSE("House"),
    SECONDARY_UNIT("Secondary Unit"),
    UNIQUE_SPACE("Unique space"),
    BED_AND_BREAKFAST("Bed and breakfast"),
    CABIN("Cabin");
    private final String typeString;
    ListingType(String type){
      this.typeString = type;
    }
    public String getType() { return this.typeString; }
  }
  public enum Field {
    ID("id"),
    TYPE("type"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    HOST_ID("hostID");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }


//  @Schema(hidden = true)
  //This works for adding a new Listing, but not for updating an existing listing.
  private int id;

  private String type;
  private double latitude;
  private double longitude;
  private int hostID;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }


  public int getHostID() {
    return hostID;
  }

  public void setHostID(int hostID) {
    this.hostID = hostID;
  }


  public Listing(){

  }

  @Override
  public String toString() {
    return "Listing{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", hostID=" + hostID +
        '}';
  }
}
