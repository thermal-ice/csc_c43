package MyBnB.repository.implementations;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Booking;
import MyBnB.repository.interfaces.IBookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
  public Integer getAllBookingsWithinRange(LocalDate startDate, LocalDate endDate, String city, String postalCode) {
    System.out.println(city + postalCode);

    String getAllBookingsQuery = "SELECT COUNT(*)\n" +
            "FROM Bookings B\n" +
            "INNER JOIN Address A ON B.listingID = A.listingID\n" +
            "WHERE (startDate BETWEEN ? AND ?) AND\n" +
            "      (endDate BETWEEN ? AND ?)\n";
    if (city != null && postalCode != null) {
      getAllBookingsQuery += " AND A." + Address.COL.CITY + " = '" + city + "' AND A." + Address.COL.POSTAL_CODE + " = '" + postalCode + "';";
    } else if (city != null) {
      getAllBookingsQuery += " AND A." + Address.COL.CITY + " = '" + city + "';";
    } else if (postalCode != null) {
      getAllBookingsQuery += " AND A." + Address.COL.POSTAL_CODE + " = '" + postalCode + "';";
    } else {
      getAllBookingsQuery += ";";
    }
    System.out.println(getAllBookingsQuery + "\n");
    return jdbcTemplate.queryForObject(getAllBookingsQuery,
            Integer.class, startDate, endDate, startDate, endDate);
  }

}
