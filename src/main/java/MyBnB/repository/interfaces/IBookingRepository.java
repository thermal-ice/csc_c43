package MyBnB.repository.interfaces;

import MyBnB.models.basic.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository {
  public List<Booking> getAllBookings();
  public Booking getBooking(int bookingID);
  public void addBooking(Booking newBooking);
  public void deleteBooking(int bookingID);
  public Integer getAllBookingsWithinRange(LocalDate startDate, LocalDate endDate, String city, String postalCode);
}
