package MyBnB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/public")
public class PublicController {

  @GetMapping("/dostuff")
  public String poop() {
    return "fjoiajfiojaiof";
  }
  @GetMapping("/morestuff")
  public String blah() {
    return "fnkjnfjanfjafiouio";
  }


}
