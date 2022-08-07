package MyBnB.repository.interfaces;

import MyBnB.controller.ListingController;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.*;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IListingRepository {

  public List<Listing> getAllListings();
  public Listing getListing(int listingID);
  public void addListing(Listing newListing);
  public void deleteListing(int listingID);
  public void updateListing(Listing updatedListing);
  public List<ListingWithDistance>  getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy);
  public List<ListingWithAddress> getListingsByPostalCode(String postalCode);
  public List<ListingWithAddress> getListingsByAddressLine(String addressLine);
  public List<Listing> getListingsWithinPriceRange(double minPrice, double maxPrice);
  public List<CountryWithListingCount> getListingCountByLocation(String country, String city, String postalCode);
  public List<CountryWithListingCount> getListingCountByCountry();
  public List<CountryCityWithListingCount> getListingCountByCountryCity();
  public List<CountryCityPostalCodeWithListingCount> getListingCountByCountryCityPostalCode();
  public List<Listing> getAllListingsByAmenities(List<String> amenities);
  public Float getSuggestedListingPrice(int listingID);

}
