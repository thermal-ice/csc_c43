package MyBnB.repository.interfaces;

import MyBnB.models.Host;
import MyBnB.models.User;

import java.util.List;

public interface UserRepository {
    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
}
