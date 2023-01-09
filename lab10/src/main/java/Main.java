import dto.User;
import java.sql.SQLException;
import repository_v2.UserRepository;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserRepository rep = UserRepository.getInstance();
//        rep.add(new User("testName", "testPass"));
//        rep.delete(new User(1, "testName", "testPass", null));
//        System.out.println(rep.findById(2));
        rep.delete(new User(123, "supeorlogin", "superpassword", null));
        System.out.println(rep.findByName("Name"));
//        User user = new User(2, "anotherName", "testPass", null);
//        rep.update(user);
    }
}
