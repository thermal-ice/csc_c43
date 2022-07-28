package MyBnB.repository.implementations;

import MyBnB.models.Booking;
import MyBnB.models.Listing;
import MyBnB.repository.interfaces.IBookingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository implements IBookingRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Booking> getAllBookings() {
    return jdbcTemplate.query("SELECT * FROM Bookings;",new BeanPropertyRowMapper<>(Booking.class));
  }

  @Override
  public Booking getBooking(int bookingID) {
    try{
      return (Booking) jdbcTemplate.
          queryForObject("SELECT * FROM Booking WHERE id=?;",
          new BeanPropertyRowMapper(Booking.class),
          bookingID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addBooking(Booking newBooking) {
    jdbcTemplate.update("INSERT INTO Bookings (renterID, hostID, listingID, endDate, startDate, status)\n" +
        "VALUES (?, ?, ?, ?, ?, ?);",
        newBooking.getRenterID(),
        newBooking.getHostID(),
        newBooking.getListingID(),
        newBooking.getEndDate(),
        newBooking.getStartDate(),
        newBooking.getStatus());
  }

  @Override
  public void deleteBooking(int bookingID) {
    jdbcTemplate.update("DELETE\n" +
        "FROM Bookings\n" +
        "WHERE id = ?;",
        bookingID);
  }



}
