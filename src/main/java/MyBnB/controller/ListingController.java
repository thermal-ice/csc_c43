package MyBnB.controller;

import MyBnB.models.Listing;
import MyBnB.models.Renter;
import MyBnB.models.User;
import MyBnB.repository.implementations.ListingRepository;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/listing")
public class ListingController {

  @Autowired
  ListingRepository listingRepository;

  @GetMapping("/all")
  public List<Listing> getAllListings() {
    return listingRepository.getAllListings();
  }

  // Note: the id of the listing is ignored. Need to figure out how to add that into the description.
  @PostMapping("/add")
  public void addListing(@RequestBody Listing newListing) {
    listingRepository.addListing(newListing);
  }


  @PutMapping("/update")
  public ResponseEntity<String> updateListing(@RequestBody Listing updatedListing){
    System.out.println(updatedListing);
    Listing existingListing= listingRepository.getListing(updatedListing.getId());

    if (existingListing == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing with this ID doesn't exist!");
    }
    if(existingListing.getHostID() != updatedListing.getHostID()){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid permissions: the hostID doesn't match this listing's ID!");
    }
    listingRepository.updateListing(updatedListing);
    return ResponseEntity.status(HttpStatus.OK).body("Success!");
  }


}