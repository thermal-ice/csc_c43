package MyBnB.repository.implementations;

import MyBnB.models.basic.Host;
import MyBnB.models.basic.User;
import MyBnB.models.composite.CountryCityHostIDListingCount;
import MyBnB.repository.interfaces.IHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import MyBnB.models.composite.CountryHostIDListingCount;

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
        // TODO: do something if host doesn't exist...
        try {
            return new Host(userRepository.getUser(id).getId());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteHost(int id) {
        jdbcTemplate.update("delete from Host where id = ?", id);
        userRepository.deleteUser(id);
    }

    @Override
    public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry() {
        return jdbcTemplate.query("SELECT country, hostID, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY country, hostID ORDER BY count DESC;",
                new BeanPropertyRowMapper(CountryHostIDListingCount.class));
    }

    @Override
    public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity() {
        return jdbcTemplate.query("SELECT country, city, hostID, COUNT(*) as count FROM Address A INNER JOIN Listing L on A.listingID = L.id GROUP BY country, city, hostID ORDER BY count DESC;",
                new BeanPropertyRowMapper(CountryCityHostIDListingCount.class));
    }
}
