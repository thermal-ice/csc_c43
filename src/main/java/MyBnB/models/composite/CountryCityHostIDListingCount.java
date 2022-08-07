package MyBnB.models.composite;

public class CountryCityHostIDListingCount {
    private String country;
    private String city;
    private Integer hostID;
    private Integer count;

    public CountryCityHostIDListingCount() {};

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getHostID() {
        return hostID;
    }

    public void setHostID(Integer hostID) {
        this.hostID = hostID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
