package repository_v2;

import dto.Group;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import service.ConnectionService;

public class UserRepository implements IRepository<User> {
    private static UserRepository INSTANCE;

    private static Connection conn;

    private UserRepository() {
        System.out.println("initializing");
        conn = ConnectionService.getInstance().getConnection();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    public void closeConntection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findByName(String searchQuery) {
        List<User> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM USERS WHERE login LIKE '%" + searchQuery + "%';";
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                User user = new User(result.getInt("user_id"), result.getString("login"),
                    result.getString("password"), null);
                list.add(user);
            }

            st.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            String query = String.format("SELECT * FROM USERS WHERE user_id = %s;", id);
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                user = new User(result.getInt("user_id"), result.getString("login"), result.getString("password"),
                    null);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User add(User user) {
        User createdUser = null;
        try {
            String query = String.format("INSERT INTO USERS (login, password) VALUES ('%s', '%s');", user.getLogin(),
                user.getPassword());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();
            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                createdUser = new User(result.getInt("user_id"), result.getString("login"), result.getString(
                    "password"), null);
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createdUser;
    }

    @Override
    public void update(User user) {
        try {
            String query = String.format("UPDATE USERS SET login = '%s', password = '%s' WHERE user_id = %s;",
                user.getLogin(), user.getPassword(), user.getId());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

            // add new groups relations
            // should check existing relations
            List<Group> groupsList = user.getGroups();
            if (groupsList != null) {
                for (Group group : groupsList) {
                    query = String.format("INSERT INTO USERS_GROUPS (user_id, group_id) VALUES ('%s', '%s');",
                        user.getId(), group.getId());
                    st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.executeUpdate();
                    st.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(User user) {
        try {
            String query =
                String.format("INSERT INTO USERS (user_id, login, password) VALUES (%s, '%s', '%s') ON " + "CONFLICT "
                    + "(user_id) DO UPDATE SET login = '%s', password = '%s';", user.getId(), user.getLogin(),
                    user.getPassword(), user.getLogin(), user.getPassword());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

            // update relevant groups
//            query = String.format("SELECT * FROM USERS_GROUPS WHERE user_id = %s;", user.getId());
//            st = conn.prepareStatement(query);
//            ResultSet result = st.executeQuery();
//            List<Integer> groupsList = new ArrayList<>();
//            while (result.next()) {
//                groupsList.add(result.getInt("group_id"));
//                // add here update via rep??
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            // remove groups relations
            String query = String.format("DELETE FROM USERS_GROUPS WHERE user_id = %s;", user.getId());
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

            query = String.format("DELETE FROM USERS WHERE user_id = %s;", user.getId());
            System.out.println(query);
            st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT count(1) from users");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean exists(User user) {
        boolean exists = false;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT count(1) FROM USERS " + "where user_id=? and "
                + "login=? and password=?");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            ResultSet result = statement.executeQuery();
            if (result.next() && result.getInt(1) > 0) {
                exists = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
