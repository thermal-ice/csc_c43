package MyBnB.repository.implementations;

import MyBnB.models.basic.Availabilities;
import MyBnB.models.basic.Listing;
import MyBnB.repository.interfaces.IAvailabilitiesRepository;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AvailabilitiesRepository implements IAvailabilitiesRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Availabilities> getAllAvailabilities() {
    return jdbcTemplate.query("SELECT * FROM Availabilities;", new BeanPropertyRowMapper<>(
        Availabilities.class));
  }

  @Override
  public List<Availabilities> getAvailabilities(int listingID, LocalDate startDate) {
    return jdbcTemplate.query("Select * From Availabilities where listingID = ? and ? <= DATE(endDate);",
        new BeanPropertyRowMapper<>(Availabilities.class),
        listingID,
        startDate);
  }


  @Override
  public String addAvailability(Availabilities availability) {
    try{
      return jdbcTemplate.queryForObject("CALL sp_addAvailability(?,?,?,?);", String.class,
          availability.getListingID(),
          availability.getPricePerNight(),
          availability.getStartDate(),
          availability.getEndDate());
    }catch (Exception e){
      return "SQL constraints violated";
    }
  }

  @Override
  public List<Availabilities> getAvailabilities(int listingID) {
    return jdbcTemplate.query("Select * From Availabilities where listingID = ?;",
        new BeanPropertyRowMapper<>(Availabilities.class),
        listingID);
  }

  @Override
  public List<Availabilities> getAvailabilities(int listingID, LocalDate startDate,
                                                LocalDate endDate) {
    return jdbcTemplate.query("Select * From Availabilities where listingID = ? and ? <= DATE(endDate) and DATE(startDate) <= ?;",
        new BeanPropertyRowMapper<>(Availabilities.class),
        listingID,
        startDate,
        endDate);
  }

  @Override
  public List<Availabilities> getAvailabilities(LocalDate startDate, LocalDate endDate) {
    return jdbcTemplate.query("Select * From Availabilities where ? <= DATE(endDate) and DATE(startDate) <=?;",
        new BeanPropertyRowMapper<>(Availabilities.class),
        startDate,
        endDate);
  }

  @Override
  public List<Availabilities> getAvailabilitiesByIDAndEndDate(int listingID, LocalDate endDate) {
    return jdbcTemplate.query("Select * From Availabilities where listingID =? AND DATE(startDate) <=?;",
        new BeanPropertyRowMapper<>(Availabilities.class),
        listingID,
        endDate);
  }

  @Override
  public List<Listing> getAvailableListings() {
    return jdbcTemplate.query("Select distinct L.* From Availabilities A inner join Listing L on A.listingID = L.id;",
        new BeanPropertyRowMapper<>(Listing.class));
  }



}
