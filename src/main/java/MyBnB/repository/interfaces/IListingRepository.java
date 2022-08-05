package MyBnB.repository.interfaces;

import MyBnB.controller.ListingController;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.CityWithListingCount;
import MyBnB.models.composite.ListingWithAddress;
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
  public List<ListingWithDistanceAndPrice>  getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy);
  public List<ListingWithAddress> getListingsByPostalCode(String postalCode);
  public List<ListingWithAddress> getListingsByAddressLine(String addressLine);
  public List<Listing> getListingsWithinPriceRange(double minPrice, double maxPrice);
  public List<CityWithListingCount> getCountListingByCity();
}
