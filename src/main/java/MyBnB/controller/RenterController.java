package MyBnB.controller;

import MyBnB.models.basic.Renter;
import MyBnB.models.basic.User;
import MyBnB.models.composite.CountryCityHostIDListingCount;
import MyBnB.models.composite.RenterIDWithBookingCount;
import MyBnB.models.composite.RenterIDWithCityWithBookingCount;
import MyBnB.models.composite.YearUserIDBookingCount;
import MyBnB.repository.implementations.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/")
public class RenterController {
    @Autowired
    RenterRepository renterRepository;

    @GetMapping("/getAllRenters")
    public List<Renter> getAllRenters() {
        return renterRepository.getAllRenters();
    }

    // NOTE: assumes valid renter id for now
    @GetMapping("/getRenter")
    public @ResponseBody User getRenter(@RequestParam("id") int id) {
        return renterRepository.getRenter(id);
    }

    @PostMapping("/addRenter")
    public void addRenter(@RequestBody Renter renter) {
        renterRepository.addRenter(renter);
    }

    @PostMapping("/deleteRenter")
    public void deleteRenter(@RequestParam("id") int id) {
        renterRepository.deleteRenter(id);
    }

//    @GetMapping("/rankByNumberOfBookingsWithinRange")
//    public List<RenterIDWithBookingCount> getRenterRankedByNumberOfBookingsWithinRange(@RequestParam("startDate") LocalDate startDate,
//                                                                                       @RequestParam("endDate") LocalDate endDate) {
//        return renterRepository.getRenterRankedByNumberOfBookingsWithinRange(startDate, endDate);
//    }
//
//    @GetMapping("/rankByNumberOfBookingsWithinRangeInCity")
//    public List<RenterIDWithCityWithBookingCount> getRenterRankedByNumberOfBookingsWithinRangePerCity(@RequestParam("startDate") LocalDate startDate,
//                                                                                                     @RequestParam("endDate") LocalDate endDate) {
//        return renterRepository.getRenterRankedByNumberOfBookingsWithinRangePerCity(startDate, endDate);
//    }
//
//    @GetMapping("/rankRentersByNumberOfCancellationsInYear")
//    public List<YearUserIDBookingCount> getRentersRankedByNumberOfCancellationsInYear() {
//        return renterRepository.getRentersRankedByNumberOfCancellationsInYear();
//    }
}