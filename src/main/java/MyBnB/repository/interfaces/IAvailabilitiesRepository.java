package MyBnB.repository.interfaces;

import MyBnB.models.basic.Availabilities;
import MyBnB.models.basic.Listing;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvailabilitiesRepository {


  public List<Availabilities> getAllAvailabilities();
  public List<Availabilities>  getAvailabilities(int listingID, LocalDate startDate);
  public void addAvailability(Availabilities availability);
  public  List<Availabilities> getAvailabilities(int listingID);
  public List<Availabilities> getAvailabilities(int listingID, LocalDate startDate, LocalDate endDate);
  public List<Availabilities> getAvailabilities(LocalDate startDate, LocalDate endDate);
  public List<Availabilities> getAvailabilitiesByIDAndEndDate(int listingID, LocalDate endDate);
  public List<Listing> getAvailableListings();


}
