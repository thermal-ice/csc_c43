package MyBnB.repository.implementations;

import MyBnB.controller.ListingController;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.ListingWithDistanceAndPrice;
import MyBnB.models.rowmappers.ListingWithDistanceAndPriceMapper;
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
      return jdbcTemplate.queryForObject("SELECT * FROM Listing WHERE id=?;",new BeanPropertyRowMapper<>(
          Listing.class), listingID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addListing(Listing newListing) {
    jdbcTemplate.update("INSERT INTO Listing (type, latitude, longitude, addressID, hostID) " +
        "values (?,?,?,?,?);",
        newListing.getType(),newListing.getLatitude(),newListing.getLongitude(),newListing.getAddressID(),
        newListing.getHostID());
  }

  @Override
  public void deleteListing(int listingID) {
    jdbcTemplate.update("DELETE FROM Listing WHERE id = ?", listingID);
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

  @Override
  public List<ListingWithDistanceAndPrice> getListingsWithinDistance(double latitude, double longitude, double radius, ListingController.OrderBy orderBy) {
    String query = "SELECT *,SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) as distance FROM Listing L INNER JOIN (SELECT listingID, MAX(pricePerNight) as price From Availabilities GROUP BY listingID) as A ON A.listingID = L.id WHERE SQRT(POW(? - latitude, 2) + POW(? - longitude,2)) < ? ";

    String endOfQuery = switch (orderBy) {
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


}
