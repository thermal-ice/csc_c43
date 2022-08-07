package MyBnB.models.composite;

public class CountryHostIDListingCount {
    private String country;
    private Integer hostID;
    private Integer count;

    public CountryHostIDListingCount() {};

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

}
