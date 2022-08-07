package MyBnB.models.composite;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;

public class RenterIDWithCityWithBookingCount {
    private String city;
    private Integer renterID;
    private Integer bookingCount;

    public RenterIDWithCityWithBookingCount() {}

    public Integer getRenterID() {
        return renterID;
    }

    public void setRenterID(Integer renterID) {
        this.renterID = renterID;
    }

    public Integer getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Integer bookingCount) {
        this.bookingCount = bookingCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
