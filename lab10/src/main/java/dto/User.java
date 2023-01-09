package dto;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Group> groups;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, List<Group> groups) {
        this.login = login;
        this.password = password;
        this.groups = groups;
    }

    public User(int id, String login, String password, List<Group> groups) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User userDTO = (User) o;
        return id == userDTO.id && Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password) && Objects.equals(groups, userDTO.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, groups);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", groups=" + groups +
            '}';
    }
}
