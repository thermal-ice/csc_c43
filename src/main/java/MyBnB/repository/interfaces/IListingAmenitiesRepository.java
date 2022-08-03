package MyBnB.repository.interfaces;

import MyBnB.models.basic.Amenities;
import MyBnB.models.basic.Listing;
import MyBnB.models.basic.ListingAmenities;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IListingAmenitiesRepository {
  public List<ListingAmenities> getAllListingAmenities();
  public List<Listing> getAllListingsForAmenity(String amenityName);
  public List<Amenities> getAllAmenitiesForListingID(int listingID);
  public void addAmenityToListing(int listingID, String amenityName);
  public void addAmenityToListing(ListingAmenities listingAmenity);
}
