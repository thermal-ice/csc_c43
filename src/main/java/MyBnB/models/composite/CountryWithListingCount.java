package MyBnB.models.composite;

public class CountryWithListingCount {
    private String country;
    private Integer count;

    public CountryWithListingCount() {};

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
