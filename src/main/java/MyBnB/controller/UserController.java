package MyBnB.controller;

import MyBnB.models.basic.User;
import MyBnB.repository.implementations.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
//@RequestMapping(path="/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // testing purposes

    @GetMapping("/check")
    @Tag(name = "blah")
    public String check() {
        return "App is running...";
    }

    @GetMapping("/getUserNames")
    public List<String> getUserNames() {
        return userRepository.getUserNames();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        System.out.println("Getting all users....");
        return userRepository.getAllUsers();
    }

    @GetMapping("/getUser")
    public @ResponseBody User getUser(@RequestParam("id") int id) {
        return userRepository.getUser(id);
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User userToAdd) {
        userRepository.addUser(userToAdd);
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestParam("id") int id) {
        userRepository.deleteUser(id);
    }
}

// endpoint setup link:
// https://stackoverflow.com/questions/32201441/how-do-i-retrieve-query-parameters-in-a-spring-boot-controller
// curl -v -X GET http://localhost:8080/getUser?id=1

//    @PostMapping(path="/addUser",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public User addUser(@RequestBody User newUser) {
//        User userAdded = userRepository.addUser(newUser);
//        return userAdded;
//    }