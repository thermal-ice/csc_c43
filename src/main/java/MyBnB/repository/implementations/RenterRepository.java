package MyBnB.repository.implementations;

import MyBnB.models.basic.Renter;
import MyBnB.models.composite.CountryHostIDListingCount;
import MyBnB.models.composite.RenterIDWithBookingCount;
import MyBnB.models.composite.RenterIDWithCityWithBookingCount;
import MyBnB.models.composite.YearUserIDBookingCount;
import MyBnB.repository.interfaces.IRenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RenterRepository implements IRenterRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Bean(name="getAllRenters")
    @Override
    public List<Renter> getAllRenters() {
//        SELECT column1, column2, ...
//        FROM table_name;

        return jdbcTemplate.query("select * from Renter;",
                new BeanPropertyRowMapper(Renter.class));
    }

    @Override
    public void addRenter(Renter renter) {
        userRepository.addUser(renter);
        jdbcTemplate.update("insert into Renter values (?);", renter.getId());
    }

    @Override
    public Renter getRenter(int id) {
        return new Renter(userRepository.getUser(id).getId());
    }

    @Override
    public void deleteRenter(int id) {
        jdbcTemplate.update("delete from Renter where id = ?", id);
        userRepository.deleteUser(id);
    }

    // using BeanPropertyRowMapper didn't allow building the statement with ?
    @Override
    public List<RenterIDWithBookingCount> getRenterRankedByNumberOfBookingsWithinRange(LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query("SELECT renterID, COUNT(id) AS bookingCount FROM Bookings\n" +
                        "WHERE (startDate BETWEEN '" + startDate + "' AND '" + endDate + "') AND (endDate BETWEEN '" + startDate + "' AND '" + endDate + "')\n" +
                        "GROUP BY renterID ORDER BY bookingCount DESC;",
                new BeanPropertyRowMapper(RenterIDWithBookingCount.class));
    }

    // TODO: change this back to bookingCount >= 2
    @Override
    public List<RenterIDWithCityWithBookingCount> getRenterRankedByNumberOfBookingsWithinRangePerCity(LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query("SELECT A.city, B.renterID, COUNT(B.id) AS bookingCount FROM Bookings B INNER JOIN Address A ON A.listingID = B.listingID\n" +
                        "WHERE (B.startDate BETWEEN '" + startDate + "' AND '" + endDate + "') AND (B.endDate BETWEEN '" + startDate + "' AND '" + endDate + "')\n" +
                        "GROUP BY A.city, B.renterID HAVING (bookingCount >= 2)\n" +
                        "ORDER BY bookingCount DESC;",
                new BeanPropertyRowMapper(RenterIDWithCityWithBookingCount.class));
    }

    @Override
    public List<YearUserIDBookingCount> getRentersRankedByNumberOfCancellationsInYear() {
        return jdbcTemplate.query("SELECT YEAR(startDate) AS year, renterID as userID, COUNT(renterID) AS cancelledCount FROM Bookings\n" +
                        "WHERE status = 'CANCELLED' GROUP BY YEAR(startDate), renterID ORDER BY year, renterID;",
                new BeanPropertyRowMapper(YearUserIDBookingCount.class));
    }
}
