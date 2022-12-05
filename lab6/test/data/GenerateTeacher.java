package data;

import com.github.javafaker.Faker;
import entities.person.Degree;
import entities.person.Student;
import entities.person.Teacher;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.TreeSet;

public class GenerateTeacher {
    static private Faker faker = new Faker();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Teacher generate() {
        return new Teacher(
            GeneratePerson.socialSecurityNo(),
            GeneratePerson.firstName(),
            GeneratePerson.lastName(),
            GeneratePerson.birthDate(),
            GeneratePerson.nationality(),
            degree(),
            hireDate()
        );
    }

    public static TreeSet<Teacher> generateAmount(int number) {
        TreeSet<Teacher> treeSet = new TreeSet<>();

        for (int i = 0; i < number; i++) {
            treeSet.add(generate());
        }

        return treeSet;
    }

    private static Degree degree() {
        return Degree.values()[new Random().nextInt(Degree.values().length)];
    }

    private static LocalDate hireDate() {
        return LocalDate.parse(sdf.format(faker.date().birthday()));
    }
}
