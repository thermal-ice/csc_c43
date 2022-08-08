package MyBnB.controller;

import MyBnB.models.basic.Review;
import MyBnB.models.composite.*;
import MyBnB.repository.implementations.*;
import MyBnB.services.MostPopularNounPhrases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/reports")
public class ReportsController {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    HostRepository hostRepository;
    @Autowired
    RenterRepository renterRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/countBookingsWithinRange")
    public Integer getCountBookingsWithinRange(@RequestParam("start-date") LocalDate startDate,
                                               @RequestParam("end-date") LocalDate endDate,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "postal-code", required = false) String postalCode) {
        return bookingRepository.getCountBookingsWithinRange(startDate, endDate, city, postalCode);
    }

    @GetMapping("/countListingsByCountry")
    public List<CountryWithListingCount> getListingCountByCountry() {
        return listingRepository.getListingCountByCountry();
    }

    @GetMapping("/countListingsByCountryAndCity")
    public List<CountryCityWithListingCount> getListingCountByCountryCity() {
        return listingRepository.getListingCountByCountryCity();
    }

    @GetMapping("/countListingsByCountryAndCityAndPostalCode")
    public List<CountryCityPostalCodeWithListingCount> getListingCountByCountryCityPostalCode() {
        return listingRepository.getListingCountByCountryCityPostalCode();
    }

    @GetMapping("/getSuggestedPrice")
    public Float getSuggestedListingPrice(@RequestParam("listingID") int listingID) {
        return listingRepository.getSuggestedListingPrice(listingID);
    }

    @GetMapping("/rankByNumberOfListingsPerCountry")
    public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry() {
        return hostRepository.getHostsRankedByNumberOfListingsPerCountry();
    }

    @GetMapping("/rankByNumberOfListingsPerCountryAndCity")
    public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity() {
        return hostRepository.getHostsRankedByNumberOfListingsPerCountryCity();
    }

    @GetMapping("/rankHostsByNumberOfCancellationsInYear")
    public List<YearUserIDBookingCount> getHostsRankedByNumberOfCancellationsInYear() {
        return hostRepository.getHostsRankedByNumberOfCancellationsInYear();
    }

    // TODO: more than 10% report

    @GetMapping("/rankByNumberOfBookingsWithinRange")
    public List<RenterIDWithBookingCount> getRenterRankedByNumberOfBookingsWithinRange(@RequestParam("startDate") LocalDate startDate,
                                                                                       @RequestParam("endDate") LocalDate endDate) {
        return renterRepository.getRenterRankedByNumberOfBookingsWithinRange(startDate, endDate);
    }

    @GetMapping("/rankByNumberOfBookingsWithinRangeInCity")
    public List<RenterIDWithCityWithBookingCount> getRenterRankedByNumberOfBookingsWithinRangePerCity(@RequestParam("startDate") LocalDate startDate,
                                                                                                      @RequestParam("endDate") LocalDate endDate) {
        return renterRepository.getRenterRankedByNumberOfBookingsWithinRangePerCity(startDate, endDate);
    }

    @GetMapping("/rankRentersByNumberOfCancellationsInYear")
    public List<YearUserIDBookingCount> getRentersRankedByNumberOfCancellationsInYear() {
        return renterRepository.getRentersRankedByNumberOfCancellationsInYear();
    }

    @GetMapping("/nounPhrases")
    public Map<Integer, Map<String,Integer>> getNounPhraseCountByListing(){

        List<Review> reviewList = reviewRepository.getAllReviews();
        return MostPopularNounPhrases.getMostPopularNounPhrases(reviewList);
    }

}
