package ant.rentathing.Classes;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;

    public User(String username, String password) throws IOException {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        UserList.getInstance().addUser(this);
    }

    @JsonCreator
    public User(@JsonProperty("id") String id,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password) {
        this.id = UUID.fromString(id);
        this.username = username;
        this.password = password;

    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
