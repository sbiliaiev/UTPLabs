package data;

import com.github.javafaker.Faker;
import entities.person.Student;
import entities.person.Teacher;
import entities.university.Department;
import entities.university.Subject;
import java.util.TreeSet;

public class GenerateSubject {
    private static Faker faker = new Faker();

    public static Subject generate(Department department, Teacher lecturer, TreeSet<Student> students) {
        return new Subject(faker.funnyName().name(), department, lecturer, students);
    }
}
