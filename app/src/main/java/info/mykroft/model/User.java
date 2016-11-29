package info.mykroft.model;

/**
 * Created by MyKroft on 11/17/2016.
 */

public class User {
    private String username;
    private String name;
    private String password;

    public User() {

    }



    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
