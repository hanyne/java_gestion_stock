package models;

public class User {
    private String pseudo;
    private String UserPassword;

    public User(String pseudo, String UserPassword) {
        this.pseudo = pseudo;
        this.UserPassword = UserPassword;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }
}
