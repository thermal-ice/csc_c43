package MyBnB.controller;

import MyBnB.controller.requestbodies.AddBookingBody;
import MyBnB.controller.requestbodies.CancelBookingBody;
import MyBnB.models.basic.Booking;
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
  public String addBooking(@RequestBody AddBookingBody requestBody) {
    return bookingRepository.addBooking(requestBody.getRenterID(),
        requestBody.getListingID(),
        requestBody.getStartDate(),
        requestBody.getEndDate());
  }


  @PostMapping("/cancel")
  public String cancelBooking(@RequestBody CancelBookingBody requestBody) {

    Booking currBooking = bookingRepository.getBooking(requestBody.getBookingID());
    if (currBooking == null || (currBooking.getHostID() != requestBody.getUserID() && currBooking.getRenterID() != requestBody.getUserID())){
      return "Invalid permissions for cancelling the booking";
    }
    return bookingRepository.cancelBooking(requestBody.getBookingID());
  }



//  @GetMapping("/count")
//  public Integer getCountBookingsWithinRange(@RequestParam("start-date") LocalDate startDate,
//                                                 @RequestParam("end-date") LocalDate endDate,
//                                                 @RequestParam(value = "city", required = false) String city,
//                                                 @RequestParam(value = "postal-code", required = false) String postalCode) {
//    return bookingRepository.getCountBookingsWithinRange(startDate, endDate, city, postalCode);
//  }

}
