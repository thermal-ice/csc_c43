package MyBnB.repository.implementations;

import MyBnB.controller.ListingController;
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
  public List<ListingWithDistanceAndPrice> getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy) {
    String query = "SELECT *,SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) as distance FROM Listing L INNER JOIN (SELECT listingID, MAX(pricePerNight) as price From Availabilities GROUP BY listingID) as A ON A.listingID = L.id WHERE SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) < ? ";

    String endOfQuery = switch (orderBy) {
      case NONE -> ";";
      case DISTANCE -> "ORDER BY distance ASC;";
      case PRICE_ASC -> "ORDER BY price ASC;";
      case PRICE_DESC -> "ORDER BY price DESC;";
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


  public List<Listing> getAllListingsByAmenities(List<String> amenities) {
    String queryToSearch = "SELECT L.id FROM Listing L ";
    String queryToAddUserSearch;
    if (amenities != null) {
      for (int i=0; i<amenities.size(); i++) {
        queryToSearch += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
        queryToSearch += "(SELECT * FROM ListingAmenities AS LA WHERE L.id = LA.listingID AND LA.amenity = '" + amenities.get(i) + "') ";
        // also update UserSearch table
        jdbcTemplate.update("UPDATE UserSearch SET searchCount=searchCount+1 WHERE amenity = ?;",
                amenities.get(i));
      }
    }
    // if amenities is null then give all listings
    System.out.println(queryToSearch);
    return jdbcTemplate.query(queryToSearch, new BeanPropertyRowMapper(Listing.class));
  }

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

  public Float getSuggestedListingPrice() {
    Float r = jdbcTemplate.queryForObject("SELECT AVG(avgPricePerNight) FROM Listing L INNER JOIN Address A on L.id = A.listingID\n" +
                    "WHERE country = 'dd';", Float.class);
    // null if no rows found
    System.out.println(r);
    return r;
  }
}
