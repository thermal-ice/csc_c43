package MyBnB.models.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import MyBnB.models.composite.CityWithListingCount;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityWithListingCountMapper implements RowMapper<CityWithListingCount> {
    @Override
    public CityWithListingCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        String city = rs.getString("city");
        Integer listingCount = rs.getInt("count");
        return new CityWithListingCount(city, listingCount);
    }
}