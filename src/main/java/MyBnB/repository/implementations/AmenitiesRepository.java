package MyBnB.repository.implementations;

import MyBnB.models.basic.Amenities;
import MyBnB.models.basic.Listing;
import MyBnB.repository.interfaces.IAmenitiesRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AmenitiesRepository implements IAmenitiesRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Amenities> getAllAmenities() {
    return jdbcTemplate.query("SELECT * FROM Amenities;",new BeanPropertyRowMapper<>(
        Amenities.class));
  }

  @Override
  public List<String> getAllAmenityNamesOfListing(int listingID) {
    return jdbcTemplate.queryForList("SELECT amenity FROM ListingAmenities AS LA WHERE LA.listingID=" + listingID + ";",
            String.class);
  }

  @Override
  public Amenities getAmenity(String amenityName) {
    try{
      return jdbcTemplate.queryForObject("SELECT * FROM Amenities WHERE name=?;",new BeanPropertyRowMapper<>(
          Amenities.class), amenityName);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addAmenity(Amenities amenity) {
    jdbcTemplate.update("insert into Amenities (name,type)" +
            "values (?,?);",
        amenity.getName(),
        amenity.getType());
  }

  @Override
  public List<Amenities> getSuggestedAmenities() {
    // remove from suggested list if num searches for amenity is 0
    return jdbcTemplate.query("SELECT name, type FROM Amenities A INNER JOIN\n" +
                    "(SELECT amenity FROM AmenitiesSearch WHERE searchCount>0 ORDER BY searchCount DESC LIMIT 10) suggestions\n" +
                    "WHERE suggestions.amenity=A.name;",
            new BeanPropertyRowMapper<>(Amenities.class));
  }

  private List<Integer> getListingIDsWithExactAmenities(int listingID, List<String> amenityNames) {
    String query = "SELECT DISTINCT L.id FROM Listing AS L INNER JOIN ListingAmenities LA on L.id = LA.listingID\n";

    if (amenityNames.isEmpty())
      return new ArrayList<>(); // return empty list

    for (int i=0; i<amenityNames.size(); i++) {
      query += (i == 0) ? "WHERE EXISTS " : "AND EXISTS ";
      query += "(SELECT * FROM ListingAmenities AS LA WHERE L.id = LA.listingID AND LA.amenity = '" + amenityNames.get(i) + "')\n";
    }
    query += "AND L.id!=" + listingID + "\n" +
            "GROUP BY L.id\n" +
            "HAVING COUNT(LA.amenity)=" + amenityNames.size() + ";";
    System.out.println(query);
    return jdbcTemplate.queryForList(query, Integer.class);
  }

  @Override
  public Float getExpectedIncreaseInPrice(int listingID, String amenityToAdd) {
    List<String> amenityNames = getAllAmenityNamesOfListing(listingID);

    // the listings amenities + the extra amenity to get the expected increase in listings price for
    amenityNames.add(amenityToAdd);
    System.out.println(amenityNames);
    List<Integer> listingIDsWithSameAmenitiesPlusAmenityToAdd = getListingIDsWithExactAmenities(listingID, amenityNames);
    if (listingIDsWithSameAmenitiesPlusAmenityToAdd.isEmpty())
      return  Float.valueOf(-1); // FIXME: no matches so what to return

    String query = "SELECT L.avgPricePerNight - AVG(sub.avgPriceWExtraAmenity) AS avgPriceWExtraAmenityOfAllMatchingListings\n" +
            "FROM (SELECT L.avgPricePerNight AS avgPriceWExtraAmenity FROM Listing L ";
    for (int i=0; i<listingIDsWithSameAmenitiesPlusAmenityToAdd.size(); i++) {
      query += (i == 0) ? "WHERE L.id=" + listingIDsWithSameAmenitiesPlusAmenityToAdd.get(i)
              : " OR L.id=" + listingIDsWithSameAmenitiesPlusAmenityToAdd.get(i);
    }
    query += ") sub, Listing L WHERE L.id=" + listingID + ";";
    System.out.println(query);

    try {
      Float expectedDifference =  jdbcTemplate.queryForObject(query, Float.class);
      return Math.abs(expectedDifference);
    } catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      return null;
    }
  }
}
