package MyBnB.models.composite;

public class CountryCityPostalCodeWithListingCount {
    private String country;
    private String city;
    private String postalCode;
    private Integer count;

    public CountryCityPostalCodeWithListingCount() {};

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
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
