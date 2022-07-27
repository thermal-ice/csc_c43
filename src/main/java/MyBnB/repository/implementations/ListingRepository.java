package MyBnB.repository.implementations;

import MyBnB.models.Listing;
import MyBnB.models.User;
import MyBnB.repository.interfaces.IListingRepository;
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
      return (Listing) jdbcTemplate.queryForObject("SELECT * FROM Listing WHERE id=?;",new BeanPropertyRowMapper(
          Listing.class), listingID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addListing(Listing newListing) {
    jdbcTemplate.update("insert into Listing (type, latitude, longitude, addressID, hostID) " +
        "values (?,?,?,?,?);",
        newListing.getType(),newListing.getLatitude(),newListing.getLongitude(),newListing.getAddressID(),
        newListing.getHostID());
  }

  @Override
  public void updateListing(Listing updatedListing) {
    jdbcTemplate.update("UPDATE Listing SET type = ?, latitude=?, longitude=?, addressID=? WHERE id = ?;",
        updatedListing.getType(),
        updatedListing.getLatitude(),
        updatedListing.getLongitude(),
        updatedListing.getAddressID(),
        updatedListing.getId());
  }


}
