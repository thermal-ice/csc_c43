package MyBnB.controller;

import MyBnB.models.basic.Availabilities;
import MyBnB.models.basic.Listing;
import MyBnB.repository.implementations.AvailabilitiesRepository;
import MyBnB.repository.interfaces.IAvailabilitiesRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/availabilities")
public class AvailabilitiesController {

  @Autowired
  AvailabilitiesRepository availabilitiesRepository;

  @GetMapping("/all")
  public List<Availabilities> getAllAvailabilities(){
    return availabilitiesRepository.getAllAvailabilities();
  }

  @GetMapping("/getAvailabilitiesByID")
  public List<Availabilities> getAllAvailabilitiesByID(@RequestParam("listingID") Integer listingID){
    return availabilitiesRepository.getAvailabilities(listingID);
  }

  @GetMapping("/getAvailabilities")
  public List<Availabilities> getAllAvailabilities(@RequestParam("listingID") Integer listingID,
                                                   @RequestParam(value = "startDate", required = false)
                                                   @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @RequestParam(value = "endDate", required = false)
                                                     @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){

    if(startDate == null && endDate == null){
      return availabilitiesRepository.getAvailabilities(listingID);
    }
    if (startDate == null){
      return availabilitiesRepository.getAvailabilitiesByIDAndEndDate(listingID,endDate);
    }
    if (endDate == null){
      return availabilitiesRepository.getAvailabilities(listingID,startDate);
    }
    return availabilitiesRepository.getAvailabilities(listingID, startDate, endDate);
  }


  @GetMapping("/getAvailabilitiesWithinRange")
  public List<Availabilities> getAvailabilitiesInRange(@RequestParam(value = "startDate")
                                                   @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @RequestParam(value = "endDate")
                                                   @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){

    return availabilitiesRepository.getAvailabilities(startDate,endDate);
  }

  @GetMapping("/allAvailableListings")
  public List<Listing> getAvailableListings(){
    return availabilitiesRepository.getAvailableListings();
  }





}
