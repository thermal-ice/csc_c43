package MyBnB.repository.implementations;

import MyBnB.controller.ListingController;
import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.*;
import MyBnB.models.rowmappers.ListingWithAddressMapper;
import MyBnB.models.rowmappers.ListingWithDistanceAndPriceMapper;
import MyBnB.repository.interfaces.IListingRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ListingRepository implements IListingRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Listing> getAllListings() {
    return jdbcTemplate.query("SELECT * FROM Listing;",new BeanPropertyRowMapper<>(Listing.class));
  }

  @Override
  public Listing getListing(int listingID) {
    try{
      return jdbcTemplate.queryForObject("SELECT * FROM Listing WHERE id=?;",new BeanPropertyRowMapper<>(
          Listing.class), listingID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addListing(Listing newListing) {
    jdbcTemplate.update("INSERT INTO Listing (type, latitude, longitude, hostID) " +
        "values (?,?,?,?);",
        newListing.getType(),newListing.getLatitude(),newListing.getLongitude(),
        newListing.getHostID());
  }

  @Override
  public void deleteListing(int listingID) {
    jdbcTemplate.update("DELETE FROM Listing WHERE id = ?", listingID);
  }

  @Override
  public void updateListing(Listing updatedListing) {
    jdbcTemplate.update("UPDATE Listing SET type = ?, latitude=?, longitude=? WHERE id = ?;",
        updatedListing.getType(),
        updatedListing.getLatitude(),
        updatedListing.getLongitude(),
        updatedListing.getId());
  }

  @Override
  public List<ListingWithDistance> getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy) {
    String query = "SELECT *,SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) as distance FROM Listing L WHERE SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) < ? ";

    String endOfQuery = switch (orderBy) {
      case NONE -> ";";
      case DISTANCE -> "ORDER BY distance ASC;";
      case PRICE_ASC -> "ORDER BY avgPricePerNight ASC;";
      case PRICE_DESC -> "ORDER BY avgPricePerNight DESC;";
    };

    String finalQuery = query + endOfQuery;

    return jdbcTemplate.query(finalQuery,new ListingWithDistanceAndPriceMapper(),
        latitude,
        longitude,
        latitude,
        longitude,
        radius);
  }

  private boolean isAlphaNumeric(String str){
    return str != null && str.matches("^[a-zA-Z0-9]*$");
  }

  @Override
  public List<ListingWithAddress> getListingsByPostalCode(String postalCode) {
    String postalCodeWithoutSpace = postalCode.replaceAll(" ", "");
    if (!isAlphaNumeric(postalCodeWithoutSpace) || postalCodeWithoutSpace.length() != 6){
      return new ArrayList<>();
    }
    String postalCodeFSA = postalCodeWithoutSpace.substring(0,3);
    return jdbcTemplate.query("Select * from Address inner join Listing L on Address.listingID = L.id Where postalCode like CONCAT(?,'%');",
        new ListingWithAddressMapper(),
        postalCodeFSA);
  }

  @Override
  public List<ListingWithAddress> getListingsByAddressLine(String addressLine) {
    return jdbcTemplate.query("Select * from Address inner join Listing L on Address.listingID = L.id Where addressLine like CONCAT('%',?,'%');",
        new ListingWithAddressMapper(),
        addressLine);
  }

  @Override
  public List<Listing> getListingsWithinPriceRange(double minPrice, double maxPrice) {
    return jdbcTemplate.query("Select distinct Listing.* From Listing inner join Availabilities A on Listing.id = A.listingID Where pricePerNight between ? AND ?;",
        new BeanPropertyRowMapper<>(Listing.class),
        minPrice,
        maxPrice);
  }

  @Override
  public List<CountryWithListingCount> getListingCountByLocation(String country, String city, String postalCode) {
    return null;
  }

//  public List<CityWithListingCount> getCountListingByCity() {
//    String query = "SELECT city, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY city ORDER BY count DESC;";
//    return jdbcTemplate.query(query, new CityWithListingCountMapper());
//  }

  public String getQueryToSearchByAmenities(List<String> amenities) {
    String queryToSearch = "SELECT L.id FROM Listing L ";
    if (amenities != null) {
      for (int i=0; i<amenities.size(); i++) {
        queryToSearch += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
        queryToSearch += "(SELECT * FROM ListingAmenities AS LA WHERE L.id = LA.listingID AND LA.amenity = '" + amenities.get(i) + "') ";
        // also update UserSearch table
        jdbcTemplate.update("UPDATE UserSearch SET searchCount=searchCount+1 WHERE amenity = ?;",
            amenities.get(i));
      }
    }
    return queryToSearch;
  }

  public List<Listing> getAllListingsByAmenities(List<String> amenities){
    String sqlQuery = getQueryToSearchByAmenities(amenities);
    return jdbcTemplate.query(sqlQuery,new BeanPropertyRowMapper<>(Listing.class));
  }


//  public List<Listing> getAllListingsByAmenities(List<String> amenities) {
//    String queryToSearch = "SELECT L.id FROM Listing L ";
//    String queryToAddUserSearch;
//    if (amenities != null) {
//      for (int i=0; i<amenities.size(); i++) {
//        queryToSearch += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
//        queryToSearch += "(SELECT * FROM ListingAmenities AS LA WHERE L.id = LA.listingID AND LA.amenity = '" + amenities.get(i) + "') ";
//        // also update UserSearch table
//        jdbcTemplate.update("UPDATE UserSearch SET searchCount=searchCount+1 WHERE amenity = ?;",
//                amenities.get(i));
//      }
//    }
//    // if amenities is null then give all listings
//    System.out.println(queryToSearch);
//    return jdbcTemplate.query(queryToSearch, new BeanPropertyRowMapper<>(Listing.class));
//  }

  public List<Listing> getListingByFullSearchQuery(String sqlQuery){
    return jdbcTemplate.query(sqlQuery,new BeanPropertyRowMapper<>(Listing.class));
  }


  public List<CountryWithListingCount> getListingCountByCountry() {
    return jdbcTemplate.query("SELECT country, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY country ORDER BY count DESC;",
            new BeanPropertyRowMapper<>(CountryWithListingCount.class));
  }

  public List<CountryCityWithListingCount> getListingCountByCountryCity() {
    return jdbcTemplate.query("SELECT country, city, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY city, country ORDER BY count DESC;",
            new BeanPropertyRowMapper<>(CountryCityWithListingCount.class));
  }
  public List<CountryCityPostalCodeWithListingCount> getListingCountByCountryCityPostalCode() {
    return jdbcTemplate.query("SELECT country, city, postalCode, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY city, country, postalCode ORDER BY count DESC;",
            new BeanPropertyRowMapper<>(CountryCityPostalCodeWithListingCount.class));
  }

  // FIXME: couldn't use getAddress from another controller - errored cause jdbcTemplate was null (?) so have to re-add it here...
  public Address getAddress(int id) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM Address WHERE listingID=?;",
              new BeanPropertyRowMapper<>(Address.class), id);
    } catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  private Float getSuggestedListingPriceGlobally() {
    System.out.println("Suggesting globally...");
    try {
      return jdbcTemplate.queryForObject("SELECT AVG(avgPricePerNight) FROM Listing L INNER JOIN Address A on L.id = A.listingID;",
              Float.class);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  private Float getSuggestedListingPriceByCountry(String country) {
    System.out.println("Suggesting based on country...");
    try {
      return jdbcTemplate.queryForObject("SELECT AVG(avgPricePerNight) FROM Listing L INNER JOIN Address A on L.id = A.listingID\n" +
                      "WHERE country='" + country + "' HAVING COUNT(L.id) > 1;",
              Float.class);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }

  }

  private Float getSuggestedListingPriceByCountryCity(String country, String city) {
    System.out.println("Suggesting based on country, city...");
    try {
      return jdbcTemplate.queryForObject("SELECT AVG(avgPricePerNight) FROM Listing L INNER JOIN Address A on L.id = A.listingID\n" +
                      "WHERE country='" + country + "' AND city='" + city + "' HAVING COUNT(L.id) > 1;",
              Float.class);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }

  }

  private Float getSuggestedListingPriceByCountryCityPostalCode(String country, String city, String postalCode) {
    System.out.println("Suggesting based on country, city, postal code...");
    System.out.println(country + city + postalCode);
    try {
      return jdbcTemplate.queryForObject("SELECT AVG(avgPricePerNight) FROM Listing L INNER JOIN Address A on L.id = A.listingID\n" +
                      "WHERE country='" + country + "' AND city='" + city + "' AND postalCode='" + postalCode + "' HAVING COUNT(L.id) > 1;",
              Float.class);
    } catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      return null;
    }
  }

  public Float getSuggestedListingPrice(int listingID) {
    // get location of listing
    Address listingAddr = getAddress(listingID);
    String country = listingAddr.getCountry();
    String city = listingAddr.getCity();
    String postalCode = listingAddr.getPostalCode();

    Float suggestedPrice = getSuggestedListingPriceByCountryCityPostalCode(country, city, postalCode);
    if (suggestedPrice == null)
      suggestedPrice = getSuggestedListingPriceByCountryCity(country, city);
    if (suggestedPrice == null)
      suggestedPrice = getSuggestedListingPriceByCountry(country);
    if (suggestedPrice == null)
      suggestedPrice = getSuggestedListingPriceGlobally();

    System.out.println(suggestedPrice);
    return suggestedPrice;
  }


}
