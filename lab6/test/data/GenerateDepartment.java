package data;

import com.github.javafaker.Faker;
import entities.person.Teacher;
import entities.university.Department;
import java.util.TreeSet;

public class GenerateDepartment {
    private static Faker faker = new Faker();

    public static Department generate(TreeSet<Teacher> employees) {
        return new Department(faker.funnyName().name(), employees);
    }
}
