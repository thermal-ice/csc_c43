package MyBnB.repository.implementations;

import MyBnB.models.basic.Availabilities;
import MyBnB.repository.interfaces.IAvailabilitiesRepository;
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
  public Availabilities getAvailability(LocalDate startDate, int listingID) {
    try{
      return jdbcTemplate.queryForObject("SELECT * FROM Availabilities WHERE startDate=? AND listingID=?;",
          new BeanPropertyRowMapper<>(
          Availabilities.class), startDate,listingID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addAvailability(Availabilities availability) {
    jdbcTemplate.update("INSERT INTO Availabilities (startDate, listingID, pricePerNight, endDate)" +
            "values (?,?,?,?);",
        availability.getStartDate(),
        availability.getListingID(),
        availability.getPricePerNight(),
        availability.getEndDate());
  }
}
