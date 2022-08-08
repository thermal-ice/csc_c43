package MyBnB.models.basic;

public class ListingsCountReport {

  private int hostCityCount;
  private String country;
  private String city;
  private int hostID;
  private int cityListingCount;

  public int getHostCityCount() {
    return hostCityCount;
  }

  public void setHostCityCount(int hostCityCount) {
    this.hostCityCount = hostCityCount;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getHostID() {
    return hostID;
  }

  public void setHostID(int hostID) {
    this.hostID = hostID;
  }

  public int getCityListingCount() {
    return cityListingCount;
  }

  public void setCityListingCount(int cityListingCount) {
    this.cityListingCount = cityListingCount;
  }

  @Override
  public String toString() {
    return "ListingsCountReport{" +
        "hostCityCount=" + hostCityCount +
        ", country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", hostID=" + hostID +
        ", cityListingCount=" + cityListingCount +
        '}';
  }
}
