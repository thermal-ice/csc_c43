package MyBnB.controller;

import MyBnB.models.basic.ListingsCountReport;
import MyBnB.repository.implementations.HostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/reports")
public class ReportsController {

  @Autowired
  HostRepository hostRepository;

  @GetMapping("/getPotentialCommericalHosts")
  public List<ListingsCountReport> getPotentialHosts(@RequestParam("withinReason") Boolean withinReason){
    return hostRepository.getSuspiciousHosts(withinReason);
  }

}
