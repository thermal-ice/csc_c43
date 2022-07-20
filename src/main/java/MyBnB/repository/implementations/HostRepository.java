package MyBnB.repository.implementations;

import MyBnB.models.Host;
import MyBnB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HostRepository implements MyBnB.repository.interfaces.HostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    public List<Host> getAllHosts() {
        return null;
    }

    // adds both to user and host table
    @Override
    public void addHost(Host host) {
        userRepository.addUser(host);
        jdbcTemplate.update("insert into Host values (?)",
                host.getId(), host.getName(), host.getBirthdate(), host.getOccupation(), host.getSin(), host.getIsActive());
    }

    // gets the user by id from User, instantiate the host
    @Override
    public Host getHost(int id) {
        return (Host) userRepository.getUser(id);
    }

    @Override
    public void deleteHost(int id) {
    }
}
