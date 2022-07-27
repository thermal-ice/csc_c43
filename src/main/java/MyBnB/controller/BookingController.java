package MyBnB.controller;

import MyBnB.models.Booking;
import MyBnB.repository.implementations.BookingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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



}
