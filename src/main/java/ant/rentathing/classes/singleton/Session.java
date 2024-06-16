package ant.rentathing.classes.singleton;

import ant.rentathing.classes.User;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Session {
    private static Session instance;
    public final ArrayList<User> activeUsers;

    public Session() {
        activeUsers = new ArrayList<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void add(User user) {
        activeUsers.add(user);
    }

    public void remove(User user) {
        activeUsers.remove(user);
    }

}
