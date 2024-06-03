package ant.rentathing.Classes;

import java.util.ArrayList;

public class Session {
    private static Session instance;
    public ArrayList<User> ActiveUsers;

    private Session() {
        ActiveUsers = new ArrayList<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void add(User user) {
        ActiveUsers.add(user);
    }

    public void remove(User user) {
        ActiveUsers.remove(user);
    }
}
