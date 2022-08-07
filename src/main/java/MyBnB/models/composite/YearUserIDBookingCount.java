package MyBnB.models.composite;

public class YearUserIDBookingCount {
    private Integer year;
    private Integer userID;
    private Integer cancelledCount;

    public YearUserIDBookingCount() {}

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCancelledCount() {
        return cancelledCount;
    }

    public void setCancelledCount(Integer cancelledCount) {
        this.cancelledCount = cancelledCount;
    }
}
