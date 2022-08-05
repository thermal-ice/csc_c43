package MyBnB.controller;

import MyBnB.models.basic.Booking;
import MyBnB.models.basic.Listing;
import MyBnB.repository.implementations.BookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//TODO test these endpoints

@RestController
@RequestMapping("/booking")
public class BookingController {

  @Autowired
  BookingRepository bookingRepository;

  @GetMapping("/all")
  public List<Booking> getAllBookings(){
    return bookingRepository.getAllBookings();
  }

  @GetMapping("/get")
  public @ResponseBody Booking getBooking(@RequestParam("id") int id){
    return bookingRepository.getBooking(id);
  }

  @PostMapping("/add")
  public void addBooking(@RequestBody Booking newBooking) {
    bookingRepository.addBooking(newBooking);
  }

  @GetMapping("/count")
  public Integer getCountBookingsWithinRange(@RequestParam("start-date") LocalDate startDate,
                                                 @RequestParam("end-date") LocalDate endDate,
                                                 @RequestParam(value = "sort", required = false) String city,
                                                 @RequestParam(value = "postal-code", required = false) String postalCode) {
    return bookingRepository.getCountBookingsWithinRange(startDate, endDate, city, postalCode);
  }

}
