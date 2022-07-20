package MyBnB.repository.implementations;

import MyBnB.models.Host;
import MyBnB.models.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RenterRepository implements MyBnB.repository.interfaces.RenterRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Renter> getAllRenters() {
        return null;
    }

    @Override
    public void addRenter(Renter renter) {
        userRepository.addUser(renter);
        jdbcTemplate.update("insert into Renter values (?)",
                renter.getId(), renter.getName(), renter.getBirthdate(), renter.getOccupation(), renter.getSin(), renter.getIsActive());
    }

    @Override
    public Renter getRenter(int id) {
        return (Renter) userRepository.getUser(id);
    }

    @Override
    public void deleteRenter(int id) {

    }
}
