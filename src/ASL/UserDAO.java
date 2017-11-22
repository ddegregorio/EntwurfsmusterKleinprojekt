package ASL;

import Domain.User;

import java.util.List;

public interface UserDAO {

    void createNewUser(User u);

    List<User> readAllUsers();

    void deleteUser(int id);

    User getUserByID(int u_id);
}
