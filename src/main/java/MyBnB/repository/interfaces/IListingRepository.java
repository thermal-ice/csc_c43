package MyBnB.repository.interfaces;

import MyBnB.controller.ListingController;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.ListingWithDistanceAndPrice;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IListingRepository {

  public List<Listing> getAllListings();
  public Listing getListing(int listingID);
  public void addListing(Listing newListing);
  public void deleteListing(int listingID);
  public void updateListing(Listing updatedListing);
<<<<<<< HEAD
  public List<ListingWithDistanceAndPrice>  getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy);

=======
>>>>>>> e94f8cda45057a85f7c0dc7b08b18f56f74568be
}
