package ant.rentathing.Classes;

import ant.rentathing.Util.JsonHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UserList {
    private static UserList instance;
    public ArrayList<User> users;

    public UserList() throws IOException {
        ArrayList<User> usersFromFile = JsonHandler.readJson(new TypeReference<>() {}, "users.json");
        users = Objects.requireNonNullElseGet(usersFromFile, ArrayList::new);
    }

    public static UserList getInstance() throws IOException {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public void addUser(User user) throws IOException {
        users.add(user);
        JsonHandler.writeJson(users, "users.json");
    }

}
