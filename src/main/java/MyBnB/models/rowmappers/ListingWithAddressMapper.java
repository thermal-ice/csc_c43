package MyBnB.models.rowmappers;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.Listing;
import MyBnB.models.composite.ListingWithAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class ListingWithAddressMapper implements RowMapper<ListingWithAddress> {
  @Override
  public ListingWithAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
    Listing currListing = new Listing();
    Address currAddress = new Address();

    currListing.setId(rs.getInt("id"));
    currListing.setType(rs.getString("type"));
    currListing.setLatitude(rs.getDouble("latitude"));
    currListing.setLongitude(rs.getDouble("longitude"));
    currListing.setHostID(rs.getInt("hostID"));

    currAddress.setAddressLine(rs.getString("addressLine"));
    currAddress.setCity(rs.getString("city"));
    currAddress.setListingID(rs.getInt("listingID"));
    currAddress.setCountry(rs.getString("country"));
    currAddress.setPostalCode(rs.getString("postalCode"));
    currAddress.setProvince_territory(rs.getString("province_territory"));

    return new ListingWithAddress(currListing,currAddress);
  }
}
