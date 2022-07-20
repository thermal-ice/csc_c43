package MyBnB.repository.implementations;

import MyBnB.models.Renter;
import MyBnB.repository.interfaces.IRenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RenterRepository implements IRenterRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Bean(name="getAllRenters")
    @Override
    public List<Renter> getAllRenters() {
//        SELECT column1, column2, ...
//        FROM table_name;

        return jdbcTemplate.query("select * from Renter;",
                new BeanPropertyRowMapper(Renter.class));
    }

    @Override
    public void addRenter(Renter renter) {
        userRepository.addUser(renter);
        jdbcTemplate.update("insert into Renter values (?);", renter.getId());
    }

    @Override
    public Renter getRenter(int id) {
        return new Renter(userRepository.getUser(id).getId());
    }

    @Override
    public void deleteRenter(int id) {
        jdbcTemplate.update("delete from Renter where id = ?", id);
        userRepository.deleteUser(id);
    }
}
