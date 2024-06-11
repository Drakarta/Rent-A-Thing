package ant.rentathing.classes.singleton;

import ant.rentathing.classes.User;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;

public class UserList extends SingletonList<User> {
    public static final String USERS_JSON = "users.json";
    private static UserList instance;

    private UserList() throws IOException {
        super(USERS_JSON, new TypeReference<>() {});
    }

    public static UserList getInstance() throws IOException {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public void addUser(User user) throws IOException {
        addItem(user, USERS_JSON);
    }
}
