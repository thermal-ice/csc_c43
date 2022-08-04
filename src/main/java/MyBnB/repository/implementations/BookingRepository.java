package MyBnB.repository.implementations;

import MyBnB.models.basic.Booking;
import MyBnB.repository.interfaces.IBookingRepository;

import java.time.LocalDate;
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
    } catch (EmptyResultDataAccessException e){
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

  @Override
  public List<Booking> getAllBookingsWithinRange(LocalDate startDate, LocalDate endDate, String sortBy) {
    // TODO change to its total number instead of all bookings
    String getAllBookingsQuery = "SELECT B.*\n" +
            "FROM Bookings B\n" +
            "INNER JOIN Address A ON B.listingID = A.listingID\n" +
            "WHERE (startDate BETWEEN ? AND ?) AND\n" +
            "      (endDate BETWEEN ? AND ?)\n";
    switch (sortBy) {
      case "city":
        return jdbcTemplate.query(getAllBookingsQuery +  "ORDER BY A.city;",
                       new BeanPropertyRowMapper<>(Booking.class),
                startDate, endDate, startDate, endDate);
      case "postalCode":
        return jdbcTemplate.query(getAllBookingsQuery + "ORDER BY A.postalCode;",
                        new BeanPropertyRowMapper<>(Booking.class),
                startDate, endDate, startDate, endDate);
      default:
        throw new IllegalArgumentException("Invalid sorting method: " + sortBy);
    }

  }

}
