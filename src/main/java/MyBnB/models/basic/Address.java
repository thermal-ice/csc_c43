package MyBnB.models.basic;

public class Address {
  public enum COL {
    LISTING_ID("España"),
    ADDRESS_LINE("addressLine"),
    CITY("city"),
    PROVINCE_TERRITORY("province_territory"),
    POSTAL_CODE("postalCode"),
    COUNTRY("country");
    private String value;
    COL (final String value) {
      this.value = value;
    }
    @Override
    public String toString() {
      return value;
    }
  }

  private int listingID;
  private String addressLine;
  private String city;
  private String province_territory;
  private String postalCode;
  private String country;

  public int getListingID() {
    return listingID;
  }

  public void setListingID(int listingID) {
    this.listingID = listingID;
  }

  public String getAddressLine() {
    return addressLine;
  }

  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince_territory() {
    return province_territory;
  }

  public void setProvince_territory(String province_territory) {
    this.province_territory = province_territory;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Address{" +
        "listingID=" + listingID +
        ", addressLine='" + addressLine + '\'' +
        ", city='" + city + '\'' +
        ", province_territory='" + province_territory + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
