package MyBnB.models.composite;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;

public class CityWithListingCount {
    private String city;
    private Integer count;

    public CityWithListingCount(String city, Integer count){
        this.city = city;
        this.count = count;
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
