package MyBnB.controller;

import MyBnB.models.basic.Booking;
import MyBnB.models.basic.Listing;
import MyBnB.repository.implementations.BookingRepository;

import java.time.LocalDate;
import java.util.List;
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

  @GetMapping("allWithinRange")
  public List<Booking> getAllBookingsWithinRange(@RequestParam("startDate") LocalDate startDate,
                                                 @RequestParam("endDate") LocalDate endDate) {
    return bookingRepository.getAllBookingsWithinRange(startDate, endDate);
  }

}
