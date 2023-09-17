package wkog.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String username;
    private String password;
    private int levelAccount;

    public UserAccount() {
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount(String username, String password, int levelAccount) {
        this.username = username;
        this.password = password;
        this.levelAccount = levelAccount;
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

    public int getLevelAccount() {
        return levelAccount;
    }

    public void setLevelAccount(int levelAccount) {
        this.levelAccount = levelAccount;
    }
}
