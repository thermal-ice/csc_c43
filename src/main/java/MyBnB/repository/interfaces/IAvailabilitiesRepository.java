package MyBnB.repository.interfaces;

import MyBnB.models.Availabilities;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvailabilitiesRepository {


  public List<Availabilities> getAllAvailabilities();
  public Availabilities getAvailability(LocalDate startDate, int listingID);
  public void addAvailability(Availabilities availability);

}
