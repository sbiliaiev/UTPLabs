package repository;

import dto.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
    static UserRepository rep;
    static int userId;
    static String userName;
    static String userPass;
    static User user;

    @BeforeEach
    void setUp() {
        rep = UserRepository.getInstance();

        userId = 456;
        userName = "TestUserName";
        userPass = "TestUserPass";

        user = new User(userId, userName, userPass, null);
    }

    @Test
    public void should_create_user() {
        int counterBefore = rep.getCount();
        User createdUser = rep.add(user);
        int counterAfter = rep.getCount();

        Assertions.assertEquals(1, counterAfter - counterBefore);
        Assertions.assertTrue(rep.exists(createdUser));

        rep.delete(createdUser);
    }

    @Test
    public void should_findById_user() {
        rep.addOrUpdate(user);
        User foundUser = rep.findById(userId);

        Assertions.assertEquals(user, foundUser);

        rep.delete(user);
    }

    @Test
    public void should_findByLogin_user() {
        rep.addOrUpdate(user);
        User createdUser = rep.add(new User(userName, userPass));
        List<User> foundUsersList = rep.findByName(userName);

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(createdUser);

        Assertions.assertEquals(list, foundUsersList);

        rep.delete(user);
        rep.delete(createdUser);
    }

    @Test
    public void should_update_user() {
        rep.addOrUpdate(user);
        String newLogin = "newLoginName";
        user.setLogin(newLogin);
        rep.update(user);
        User foundUser = rep.findById(userId);

        Assertions.assertEquals(user, foundUser);

        rep.delete(user);
    }

    @Test
    public void should_delete_user() {
        int counterBefore = rep.getCount();

        rep.addOrUpdate(user);
        rep.delete(user);

        int counterAfter = rep.getCount();

        User foundUser = rep.findById(userId);

        Assertions.assertNull(foundUser);
        Assertions.assertEquals(counterBefore, counterAfter);
    }
}