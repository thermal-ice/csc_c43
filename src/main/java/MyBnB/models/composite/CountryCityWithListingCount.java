package MyBnB.models.composite;

public class CountryCityWithListingCount {
    private String country;
    private String city;
    private Integer count;

    public CountryCityWithListingCount() {};

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
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
