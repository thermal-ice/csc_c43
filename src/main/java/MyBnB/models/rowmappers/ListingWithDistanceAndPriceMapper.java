package MyBnB.models.rowmappers;

import MyBnB.models.basic.Listing;
import MyBnB.models.composite.ListingWithDistanceAndPrice;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ListingWithDistanceAndPriceMapper implements RowMapper<ListingWithDistanceAndPrice> {
  @Override
  public ListingWithDistanceAndPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
    Listing currListing = new Listing();
    currListing.setId(rs.getInt("id"));
    currListing.setHostID(rs.getInt("hostID"));
    currListing.setLatitude(rs.getDouble("latitude"));
    currListing.setLongitude(rs.getDouble("longitude"));
    currListing.setType(rs.getString("type"));

    ListingWithDistanceAndPrice newListingWithDistanceAndPrice = new ListingWithDistanceAndPrice(currListing);
    newListingWithDistanceAndPrice.setDistanceToUser(rs.getDouble("distance"));
    newListingWithDistanceAndPrice.setPrice(rs.getDouble("price"));
    return newListingWithDistanceAndPrice;
  }
}
