package dto;

import java.util.List;
import java.util.Objects;

public class Group {
    private int id;
    private String name;
    private String description;
    private List<User> users;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(String name, String description, List<User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Group(int id, String name, String description, List<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group groupDTO = (Group) o;
        return id == groupDTO.id && Objects.equals(name, groupDTO.name) && Objects.equals(description, groupDTO.description) && Objects.equals(users, groupDTO.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, users);
    }

    @Override
    public String toString() {
        return "Group{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", users=" + users +
            '}';
    }
}
