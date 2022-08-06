package MyBnB.controller;

import MyBnB.models.basic.Host;
import MyBnB.models.composite.CityWithListingCount;
import MyBnB.models.composite.ListingWithAddress;
import MyBnB.repository.implementations.HostRepository;
import MyBnB.models.basic.Amenities;
import MyBnB.models.basic.Listing;
import MyBnB.models.basic.ListingAmenities;
import MyBnB.models.composite.ListingWithDistanceAndPrice;
import MyBnB.repository.implementations.ListingAmenitiesRepository;
import MyBnB.repository.implementations.ListingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/listing")
public class ListingController {

  @Autowired
  ListingRepository listingRepository;

  @Autowired
  ListingAmenitiesRepository listingAmenitiesRepository;

  public enum OrderBy{
    DISTANCE,
    PRICE_ASC,
    PRICE_DESC
  }

  @GetMapping("/all")
  public List<Listing> getAllListings() {
    return listingRepository.getAllListings();
  }

  // Note: the id of the listing is ignored. Need to figure out how to add that into the description.
  @PostMapping("/add")
  public void addListing(@RequestBody Listing newListing) {
    listingRepository.addListing(newListing);
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteListing(@RequestParam("listingId")  int listingID){
    System.out.println(listingID);

    Listing existingListing = listingRepository.getListing(listingID);

    if (existingListing == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }
    // FIXME: invalid permissions if host id doesn't match with listing id
    listingRepository.deleteListing(listingID);
    return ResponseEntity.status(HttpStatus.OK).body("Success!");
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateListing(@RequestBody Listing updatedListing){
    System.out.println(updatedListing);
    Listing existingListing= listingRepository.getListing(updatedListing.getId());

    if (existingListing == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }
    if(existingListing.getHostID() != updatedListing.getHostID()){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid permissions: the hostID doesn't match this listing's ID!");
    }
    listingRepository.updateListing(updatedListing);
    return ResponseEntity.status(HttpStatus.OK).body("Success!");
  }

  @GetMapping("/amenities")
  public List<Amenities> getAllAmenitiesForListing(@RequestParam("listingId")  int listingID){
    return listingAmenitiesRepository.getAllAmenitiesForListingID(listingID);
  }

  @GetMapping("/allListingAmenities")
  public List<ListingAmenities> getAllListingAmenities(){
    return listingAmenitiesRepository.getAllListingAmenities();
  }

  @GetMapping("/getWithinDistance")
  public List<ListingWithDistanceAndPrice> getAllListingsWithinDistance(@RequestParam("latitude")  double latitude,
                                                                        @RequestParam("longitude")  double longitude,
                                                                        @RequestParam(value = "radius", defaultValue = "50")  double radius,
                                                                        @RequestParam(value = "OrderBy") OrderBy orderBy){
    return listingRepository.getListingsWithinDistance(latitude,longitude,radius, orderBy);
  }


  @GetMapping("/getAdjacentPostalCodes")
  public List<ListingWithAddress> getAllListingsWithAdjacentPostalCodes(@RequestParam("postalcode") String postalcode){
    return listingRepository.getListingsByPostalCode(postalcode);
  }

  @GetMapping("/getByAddressLine")
  public List<ListingWithAddress> getAllListingsWithMatchingAddressLine(@RequestParam("addressLine") String addressLine){
    return listingRepository.getListingsByAddressLine(addressLine);
  }

  @GetMapping("/getByPriceRange")
  public List<Listing> getAllListingsWithinPriceRange(@RequestParam("minPrice") double minPrice,
                                                                        @RequestParam("maxPrice") double maxPrice){
    return listingRepository.getListingsWithinPriceRange(minPrice, maxPrice);
  }

  @GetMapping("/getByAmenities")
  public List<Listing> getAllListingsByAmenities(@RequestParam(value = "amenities", required = false) List<String> amenities) {
    System.out.println(amenities);
    return listingRepository.getAllListingsByAmenities(amenities);
  }










//  @GetMapping("/countByCity")
//  public List<CityWithListingCount> getCountListingByCity() {
//    return listingRepository.getCountListingByCity();
//  }
  // TODO: do this for country +
}
