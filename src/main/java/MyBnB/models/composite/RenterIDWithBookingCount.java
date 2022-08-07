package MyBnB.models.composite;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;

public class RenterIDWithBookingCount {
    private Integer renterID;
    private Integer bookingCount;

    public RenterIDWithBookingCount() {}

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
}
