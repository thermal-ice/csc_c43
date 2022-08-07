package MyBnB.controller;

import MyBnB.models.basic.Host;
import MyBnB.models.composite.CountryCityHostIDListingCount;
import MyBnB.models.composite.CountryHostIDListingCount;
import MyBnB.repository.implementations.HostRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class HostController {
    @Autowired
    HostRepository hostRepository;


    @GetMapping("/getAllHosts")
    @Tag(name="stuff")
    public List<Host> getAllHosts() {
        return hostRepository.getAllHosts();
    }

    // NOTE: assumes valid renter id for now
    @GetMapping("/getHost")
    public @ResponseBody Host getHost(@RequestParam("id") int id) {
        return hostRepository.getHost(id);
    }

    @PostMapping("/addHost")
    public void addHost(@RequestBody Host host) {
        hostRepository.addHost(host);
    }

    @PostMapping("/deleteHost")
    public void deleteHost(@RequestParam("id") int id) {
        hostRepository.deleteHost(id);
    }

    @GetMapping("/rankByNumberOfListingsPerCountry")
    public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry() {
        return hostRepository.getHostsRankedByNumberOfListingsPerCountry();
    }

    @GetMapping("/rankByNumberOfListingsPerCountryCity")
    public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity() {
        return hostRepository.getHostsRankedByNumberOfListingsPerCountryCity();
    }
}
