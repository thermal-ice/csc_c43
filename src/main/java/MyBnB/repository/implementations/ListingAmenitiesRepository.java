package MyBnB.repository.implementations;

import MyBnB.models.Amenities;
import MyBnB.models.Listing;
import MyBnB.models.ListingAmenities;
import MyBnB.repository.interfaces.IListingAmenitiesRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ListingAmenitiesRepository implements IListingAmenitiesRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  private BeanPropertyRowMapper<ListingAmenities> rowMapper;

  public ListingAmenitiesRepository(){
    this.rowMapper = new BeanPropertyRowMapper<>(ListingAmenities.class);
  }

  @Override
  public List<ListingAmenities> getAllListingAmenities() {
    return jdbcTemplate.query("SELECT listingID,amenity FROM ListingAmenities",
        this.rowMapper);
  }

  @Override //Might need to rework this
  public List<Listing> getAllListingsForAmenity(String amenityName) {
    return jdbcTemplate.query("SELECT * FROM ListingAmenities NATURAL JOIN Listing WHERE amenity=?",
        new BeanPropertyRowMapper<>(Listing.class),
        amenityName);
  }

  // All 4 of these methods work!

  @Override
  public List<Amenities> getAllAmenitiesForListingID(int listingID) {
    return jdbcTemplate.query("SELECT * FROM ListingAmenities INNER JOIN Amenities ON ListingAmenities.amenity = Amenities.name WHERE listingID= ?;",
        new BeanPropertyRowMapper<>(Amenities.class),
        listingID);
  }

//  @Override
//  public List<Amenities> getAllAmenitiesForListingID(int listingID) {
//    return jdbcTemplate.query("SELECT * FROM ListingAmenities INNER JOIN Amenities ON ListingAmenities.amenity = Amenities.name WHERE listingID= ?;",
//        new BeanPropertyRowMapper<>(Amenities.class),
//        listingID);
//  }

//  @Override
//  public List<Amenities> getAllAmenitiesForListingID(int listingID) {
//    return jdbcTemplate.query("SELECT name as name,type as type FROM ListingAmenities INNER JOIN Amenities ON ListingAmenities.amenity = Amenities.name WHERE listingID= ?;",
//        new BeanPropertyRowMapper<>(Amenities.class),
//        listingID);
//  }



//  @Override
//  public List<Amenities> getAllAmenitiesForListingID(int listingID) {
//    return jdbcTemplate.query(
//        "SELECT name as name,type as type FROM ListingAmenities INNER JOIN Amenities ON ListingAmenities.amenity = Amenities.name WHERE listingID= ?;",
//        new RowMapper<Amenities>() {
//          @Override
//          public Amenities mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Amenities currAmenity = new Amenities();
//            currAmenity.setName(rs.getString("name"));
//            currAmenity.setType(rs.getString("type"));
//            return currAmenity;
//          }
//        },
//        listingID);
//  }



  @Override
  public void addAmenityToListing(int listingID, String amenityName) {

  }

  @Override
  public void addAmenityToListing(ListingAmenities listingAmenity) {

  }
}
