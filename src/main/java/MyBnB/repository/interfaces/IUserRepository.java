package MyBnB.repository.interfaces;

import MyBnB.models.basic.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
    public List<User> getAllUsersByStatus ();
}
