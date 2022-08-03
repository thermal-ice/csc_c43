package MyBnB.repository.interfaces;

import MyBnB.models.basic.Listing;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IListingRepository {

  public List<Listing> getAllListings();
  public Listing getListing(int listingID);
  public void addListing(Listing newListing);
  public void updateListing(Listing updatedListing);

}
