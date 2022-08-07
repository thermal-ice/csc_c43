package MyBnB.controller;

import MyBnB.models.basic.Renter;
import MyBnB.models.basic.User;
import MyBnB.repository.implementations.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class ReviewController {
//    @Autowired
//    ReviewController reviewController;
//
//    @GetMapping("/getAllRenters")
//    public List<Renter> getAllRenters() {
//        return reviewController.getAllRenters();
//    }
//
//    // NOTE: assumes valid renter id for now
//    @GetMapping("/getRenter")
//    public @ResponseBody User getRenter(@RequestParam("id") int id) {
//        return reviewController.getRenter(id);
//    }
//
//    @PostMapping("/addRenter")
//    public void addRenter(@RequestBody Renter renter) {
//        reviewController.addRenter(renter);
//    }
//
//    @PostMapping("/deleteRenter")
//    public void deleteRenter(@RequestParam("id") int id) {
//        reviewController.deleteRenter(id);
//    }
}