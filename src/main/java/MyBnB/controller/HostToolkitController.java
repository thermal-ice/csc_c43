package MyBnB.controller;

import MyBnB.models.basic.Amenities;
import MyBnB.repository.implementations.AmenitiesRepository;
import MyBnB.repository.implementations.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/hostToolkit")
public class HostToolkitController {
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    AmenitiesRepository amenitiesRepository;

    @GetMapping("/getSuggestedPrice")
    public Float getSuggestedListingPrice(@RequestParam("listingID") int listingID) {
      return listingRepository.getSuggestedListingPrice(listingID);
    }

    @GetMapping("/getSuggestedAmenities")
    public List<Amenities> getSuggestedAmenities() {
        return amenitiesRepository.getSuggestedAmenities();
    }

    @GetMapping("/getExpectedIncreaseInPrice")
    public Float getExpectedIncreaseInPrice(@RequestParam("listingID") int listingID, @RequestParam("amenityToAdd") String amenityToAdd) {
        return amenitiesRepository.getExpectedIncreaseInPrice(listingID, amenityToAdd);
    }

}
