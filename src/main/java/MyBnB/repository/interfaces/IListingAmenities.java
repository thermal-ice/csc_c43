package MyBnB.repository.interfaces;

import MyBnB.models.Amenities;
import MyBnB.models.Listing;
import MyBnB.models.ListingAmenities;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IListingAmenities {
  public List<ListingAmenities> getAllListingAmenities();
  public List<Listing> getAllListingsForAmenity(String amenityName);
  public List<Amenities> getAllAmenitiesForListingID(int listingID);
  public void addAmenityToListing(int listingID, String amenityName);
  public void addAmenityToListing(ListingAmenities listingAmenity);
}
