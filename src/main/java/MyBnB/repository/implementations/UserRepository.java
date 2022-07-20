package MyBnB.repository.implementations;
import MyBnB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements MyBnB.repository.interfaces.UserRepository {
    // auto-wiring jdbc template using the properties configured in app.prop
    // spring automatically detects and creates jdbc template obj using the configuration
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<>();
        String query = "select name from User;";
        userNames.addAll(jdbcTemplate.queryForList(query, String.class));
        return userNames;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from User;",
                new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User getUser(int id) {
        return (User) jdbcTemplate.queryForObject("select * from User where id=?;",
                new BeanPropertyRowMapper(User.class), id);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert into User values (?, ?, ?, ?, ?, ?);",
                user.getId(), user.getName(), user.getBirthdate(), user.getOccupation(), user.getSin(), user.getIsActive());
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("delete books where id = ?", id);
    }
}


// failed attempts
//    @Override
//    public void addUser(User user) {
//        // maps each row attribute to a User object fields based on the names
//        System.out.println(user.getId() + user.getName() + user.getBirthdate() + user.getOccupation() + user.getSin() + user.getIsActive());
////        String query = "insert into `User` values (`id`, `name`, `birthdate`, `occupation`, `sin`, `isActive`) (?, ?, ?, ?, ?, ?);";
////        return jdbcTemplate.update(query, user.getId(), user.getName(), user.getBirthdate(), user.getOccupation(), user.getSin(), user.getIsActive());
////        List<User> users = jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
//        jdbcTemplate.update("insert into User values (?, ?, ?, ?, ?, ?);",
//                user.getId(), user.getName(), user.getBirthdate(), user.getOccupation(), user.getSin(), user.getIsActive());
//    }
// return request status later
//curl -H "Content-Type: application/json" -X POST -d {\"username\":\"mkyong\",\"password\":\"abc\"} http://localhost:8080/api/login/
// curl -H "Content-Type: application/json" -X POST -d '{"id":"9","name":"ava","birthdate":"2000-01-31","sin":"111 111 111", "isActive":1}' http://localhost:8080/addUser
// {"id":1,"name":"Avery Marchmount","birthdate":"2000-05-21T04:00:00.000+00:00","occupation":"architect","sin":"233 472 976","isActive":1}