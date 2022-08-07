package MyBnB.models.rowmappers;

import MyBnB.models.basic.Listing;
import MyBnB.models.composite.ListingWithDistance;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ListingWithDistanceAndPriceMapper implements RowMapper<ListingWithDistance> {
  @Override
  public ListingWithDistance mapRow(ResultSet rs, int rowNum) throws SQLException {
    Listing currListing = new Listing();
    currListing.setId(rs.getInt("id"));
    currListing.setHostID(rs.getInt("hostID"));
    currListing.setLatitude(rs.getDouble("latitude"));
    currListing.setLongitude(rs.getDouble("longitude"));
    currListing.setType(rs.getString("type"));
    currListing.setAvgPricePerNight(rs.getDouble("avgPricePerNight"));

    ListingWithDistance newListingWithDistance = new ListingWithDistance(currListing);
    newListingWithDistance.setDistanceToUser(rs.getDouble("distance"));
    return newListingWithDistance;
  }
}
