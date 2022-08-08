package MyBnB.repository.implementations;

import MyBnB.models.basic.Host;
import MyBnB.models.basic.ListingsCountReport;
import MyBnB.models.basic.User;
import MyBnB.models.composite.CountryCityHostIDListingCount;
import MyBnB.models.composite.YearUserIDBookingCount;
import MyBnB.repository.interfaces.IHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import MyBnB.models.composite.CountryHostIDListingCount;

import java.util.List;

@Repository
public class HostRepository implements IHostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Bean(name="getAllHosts")
    public List<Host> getAllHosts() {
        return jdbcTemplate.query("select * from User where id in (select id from Host)",
                new BeanPropertyRowMapper(User.class));
    }

    // adds both to user and host table
    @Override
    public void addHost(Host host) {
        userRepository.addUser(host);
        jdbcTemplate.update("insert into Host values (?)", host.getId());
    }

    // gets the user by id from User, instantiate the host
    @Override
    public Host getHost(int id) {
        // TODO: do something if host doesn't exist...
        try {
            return new Host(userRepository.getUser(id).getId());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteHost(int id) {
        jdbcTemplate.update("delete from Host where id = ?", id);
        userRepository.deleteUser(id);
    }

    @Override
    public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry() {
        return jdbcTemplate.query("SELECT country, hostID, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY country, hostID ORDER BY country, hostID, count DESC;",
                new BeanPropertyRowMapper(CountryHostIDListingCount.class));
    }

    @Override
    public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity() {
        return jdbcTemplate.query("SELECT country, city, hostID, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY country, city, hostID ORDER BY country, city, hostID, count DESC;",
                new BeanPropertyRowMapper<>(CountryCityHostIDListingCount.class));
    }

    @Override
    public List<YearUserIDBookingCount> getHostsRankedByNumberOfCancellationsInYear() {
        return jdbcTemplate.query("SELECT YEAR(startDate) AS year, hostID as userID, COUNT(hostID) AS cancelledCount FROM Bookings\n" +
                        "WHERE status = 'CANCELLED' GROUP BY YEAR(startDate), hostID ORDER BY year DESC;",
                new BeanPropertyRowMapper(YearUserIDBookingCount.class));
    }

  @Override
  public List<ListingsCountReport> getSuspiciousHosts(boolean withinReason) {
    if (withinReason){
      return jdbcTemplate.query("Select * From (Select COUNT(listingID) as hostcityCount,country,city,hostID from Listing inner join Address A on Listing.id = A.listingID Group by country, city, hostID) HostCount inner join\n" +
          "    (Select Count(listingID) as citylistingCount, country,city From Listing inner join Address A on Listing.id = A.listingID group by country, city) CityCount\n" +
          "        On HostCount.city = CityCount.city AND HostCount.country = CityCount.country\n" +
          "        AND HostCount.hostcityCount  > CityCount.citylistingCount * 0.1\n" +
          "        AND CityCount.citylistingCount > 20;", new BeanPropertyRowMapper<>(ListingsCountReport.class));
    }

    return jdbcTemplate.query("Select * From (Select COUNT(listingID) as hostcityCount,country,city,hostID from Listing inner join Address A on Listing.id = A.listingID Group by country, city, hostID) HostCount inner join\n" +
        "    (Select Count(listingID) as citylistingCount, country,city From Listing inner join Address A on Listing.id = A.listingID group by country, city) CityCount\n" +
        "        On HostCount.city = CityCount.city AND HostCount.country = CityCount.country\n" +
        "        AND HostCount.hostcityCount  > CityCount.citylistingCount * 0.1;", new BeanPropertyRowMapper<>(ListingsCountReport.class));
  }


}
