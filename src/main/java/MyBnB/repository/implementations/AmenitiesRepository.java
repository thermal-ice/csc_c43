package MyBnB.repository.implementations;

import MyBnB.models.basic.Amenities;
import MyBnB.repository.interfaces.IAmenitiesRepository;
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
}
