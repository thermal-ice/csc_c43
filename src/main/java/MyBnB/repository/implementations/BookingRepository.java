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
          queryForObject("SELECT * FROM Bookings WHERE id=?;",
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
  public Integer getCountBookingsWithinRange(LocalDate startDate, LocalDate endDate, String city, String postalCode) {
    System.out.println(city + postalCode);

    String query = "SELECT COUNT(*)\n" +
            "FROM Bookings B\n" +
            "INNER JOIN Address A ON B.listingID = A.listingID\n" +
            "WHERE (startDate BETWEEN ? AND ?) AND\n" +
            "      (endDate BETWEEN ? AND ?)\n";
    if (city != null)
      query += " AND A." + Address.Field.CITY + " = '" + city + "'";
    if (postalCode != null)
      query += " AND A." + Address.Field.POSTAL_CODE + " = '" + postalCode + "'";
    query += ";";

    System.out.println(query + "\n");
    return jdbcTemplate.queryForObject(query,
            Integer.class, startDate, endDate, startDate, endDate);
  }

}
