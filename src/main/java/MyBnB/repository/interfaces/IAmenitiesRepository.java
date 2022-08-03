package MyBnB.repository.interfaces;

import MyBnB.models.basic.Amenities;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IAmenitiesRepository {

  public List<Amenities> getAllAmenities();
  public Amenities getAmenity(String amenityName);
  public void addAmenity(Amenities amenity);

}
