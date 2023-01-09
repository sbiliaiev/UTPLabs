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

public class GroupRepository implements IRepository<Group> {
    private static GroupRepository INSTANCE;

    private static Connection conn;

    private GroupRepository() {
        System.out.println("initializing");
        conn = ConnectionService.getInstance().getConnection();
    }

    public static GroupRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GroupRepository();
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
    public List<Group> findByName(String searchQuery) {
        List<Group> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM GROUPS WHERE name LIKE '%" + searchQuery + "%';";
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                Group group = new Group(result.getInt("group_id"), result.getString("name"), result.getString(
                    "description"), null);
                list.add(group);
            }

            st.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Group findById(int id) {
        Group group = null;
        try {
            String query = String.format("SELECT * FROM GROUPS WHERE group_id = %s;", id);
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet result = st.executeQuery();
            result.next();
            group = new Group(result.getInt("group_id"), result.getString("name"), result.getString("description"),
                null);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public Group add(Group group) {
        Group createdGroup = null;
        try {
            String query = String.format("INSERT INTO GROUPS (name, description) VALUES ('%s', '%s');",
                group.getName(), group.getDescription());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();
            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                createdGroup = new Group(result.getInt("group_id"), result.getString("name"), result.getString(
                    "description"), null);
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createdGroup;
    }

    @Override
    public void update(Group group) {
        try {
            String query = String.format("UPDATE GROUPS SET name = '%s', description = '%s' WHERE group_id = %s;",
                group.getName(), group.getDescription(), group.getId());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

            // add new users relations
            List<User> usersList = group.getUsers();
            if (usersList != null) {
                for (User user : usersList) {
                    query = String.format("INSERT INTO USERS_GROUPS (user_id, group_id) VALUES ('%s', '%s');", user.getId(), group.getId());
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
    public void addOrUpdate(Group group) {
        try {
            String query = String.format("INSERT INTO GROUPS (group_id, name, description) VALUES (%s, '%s', '%s') ON "
                + "CONFLICT " + "(group_id) DO UPDATE SET name = '%s', description = '%s';", group.getId(),
                group.getName(), group.getDescription(), group.getName(), group.getDescription());
            System.out.println(query);
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

//            // update relevant groups
//            query = String.format("SELECT * FROM GROUPS WHERE group_id = %s;", group.getId());
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
    public void delete(Group group) {
        try {
            // remove users relations
            String query = String.format("DELETE FROM USERS_GROUPS WHERE group_id = %s;", group.getId());
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            st.close();

            query = String.format("DELETE FROM GROUPS WHERE group_id = %s;", group.getId());
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
            PreparedStatement statement = conn.prepareStatement("SELECT count(1) from groups");
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
    public boolean exists(Group group) {
        boolean exists = false;
        try {
            PreparedStatement statement =
                conn.prepareStatement("SELECT count(1) FROM groups " + "where group_id=? " + "and " + "name=? and " + "description=?");
            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
            statement.setString(3, group.getDescription());
            ResultSet result = statement.executeQuery();
            if (result.next() && result.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
