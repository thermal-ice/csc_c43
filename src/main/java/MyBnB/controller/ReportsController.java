package MyBnB.controller;

import MyBnB.models.basic.Review;
import MyBnB.models.composite.*;
import MyBnB.repository.implementations.*;
import MyBnB.services.MostPopularNounPhrases;
import org.springframework.beans.factory.annotation.Autowired;
import MyBnB.models.basic.ListingsCountReport;
import MyBnB.repository.implementations.HostRepository;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(path = "/reports")
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
  public Integer getCountBookingsWithinRange(@RequestParam("start-date")
                                               @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam("end-date")
                                              @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate,
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

//  @GetMapping("/getSuggestedPrice")
//  public Float getSuggestedListingPrice(@RequestParam("listingID") int listingID) {
//    return listingRepository.getSuggestedListingPrice(listingID);
//  }

  @GetMapping("/rankHostsByNumberOfListingsPerCountry")
  public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry() {
    return hostRepository.getHostsRankedByNumberOfListingsPerCountry();
  }

  @GetMapping("/rankHostsByNumberOfListingsPerCountryAndCity")
  public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity() {
    return hostRepository.getHostsRankedByNumberOfListingsPerCountryCity();
  }

  @GetMapping("/rankHostsByNumberOfCancellationsInYear")
  public List<YearUserIDBookingCount> getHostsRankedByNumberOfCancellationsInYear() {
    return hostRepository.getHostsRankedByNumberOfCancellationsInYear();
  }

  @GetMapping("/rankRentersByNumberOfCancellationsInYear")
  public List<YearUserIDBookingCount> getRentersRankedByNumberOfCancellationsInYear() {
    return renterRepository.getRentersRankedByNumberOfCancellationsInYear();
  }

  @GetMapping("/getPotentialCommericalHosts")
  public List<ListingsCountReport> getPotentialHosts(@RequestParam("withinReason") Boolean withinReason) {
    return hostRepository.getSuspiciousHosts(withinReason);
  }

  @GetMapping("/rankByNumberOfBookingsWithinRange")
  public List<RenterIDWithBookingCount> getRenterRankedByNumberOfBookingsWithinRange(
      @RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return renterRepository.getRenterRankedByNumberOfBookingsWithinRange(startDate, endDate);
  }

  @GetMapping("/rankByNumberOfBookingsWithinRangePerCity")
  public List<RenterIDWithCityWithBookingCount> getRenterRankedByNumberOfBookingsWithinRangePerCity(
      @RequestParam("startDate")
      @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  LocalDate startDate,
      @RequestParam("endDate")
      @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  LocalDate endDate,
      @RequestParam("withinReasonMinBookingCount") int withinReasonCount) {
    return renterRepository.getRenterRankedByNumberOfBookingsWithinRangePerCity(startDate, endDate, withinReasonCount);
  }

  @GetMapping("/nounPhrases")
  public Map<Integer, Map<String, Integer>> getNounPhraseCountByListing() {

    List<Review> reviewList = reviewRepository.getAllReviews();
    return MostPopularNounPhrases.getMostPopularNounPhrases(reviewList);
  }
}
