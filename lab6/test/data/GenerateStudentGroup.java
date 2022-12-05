package data;

import com.github.javafaker.Faker;
import entities.person.Student;
import entities.university.StudentGroup;
import java.util.TreeSet;

public class GenerateStudentGroup {
    private static Faker faker = new Faker();

    public static StudentGroup generate(TreeSet<Student> students) {
        return new StudentGroup(faker.name().title(), students);
    }
}
