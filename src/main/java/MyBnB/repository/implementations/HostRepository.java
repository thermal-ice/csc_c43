package MyBnB.repository.implementations;

import MyBnB.models.Host;
import MyBnB.models.Renter;
import MyBnB.models.User;
import MyBnB.repository.interfaces.IHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostRepository implements IHostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Bean(name="getAllHosts")
    public List<Host> getAllHosts() {
        return jdbcTemplate.query("select * from User where id in (select id from Host)",
                new BeanPropertyRowMapper(User.class));
    }

    // adds both to user and host table
    @Override
    public void addHost(Host host) {
        userRepository.addUser(host);
        jdbcTemplate.update("insert into Host values (?)", host.getId());
    }

    // gets the user by id from User, instantiate the host
    @Override
    public Host getHost(int id) {
        return new Host(userRepository.getUser(id).getId());
    }

    @Override
    public void deleteHost(int id) {
        jdbcTemplate.update("delete from Host where id = ?", id);
        userRepository.deleteUser(id);
    }
}