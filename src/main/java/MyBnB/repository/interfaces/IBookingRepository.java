package MyBnB.repository.interfaces;

import MyBnB.models.Booking;
import java.util.List;

public interface IBookingRepository {
  public List<Booking> getAllBookings();
  public Booking getBooking(int bookingID);
  public void addBooking(Booking newBooking);
  public void deleteBooking(int bookingID);
}