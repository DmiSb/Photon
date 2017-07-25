package ru.dmisb.photon.data.network.req;

@SuppressWarnings("unused")
public class RegisterReq {
    private String name;
    private String login;
    private String email;
    private String password;

    public RegisterReq(String name, String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
