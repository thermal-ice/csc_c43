package MyBnB.controller;

import MyBnB.models.basic.Amenities;
import MyBnB.models.basic.Host;
import MyBnB.repository.implementations.AmenitiesRepository;
import MyBnB.repository.implementations.HostRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class AmenitiesController {
    @Autowired
    AmenitiesRepository amenitiesRepository;


    @GetMapping("/getAllAmenities")
    @Tag(name="stuff")
    public List<Amenities> getAllAmenities() { return amenitiesRepository.getAllAmenities(); }

    // NOTE: assumes valid renter id for now
    @GetMapping("/getAmenity")
    public @ResponseBody Amenities getAmenity(@RequestParam("name") String name) {
        return amenitiesRepository.getAmenity(name);
    }

    @PostMapping("/addAmenity")
    public void addAmenity(@RequestBody Amenities amenity) {
        amenitiesRepository.addAmenity(amenity);
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
