package data;

import com.github.javafaker.Faker;
import entities.person.Student;
import java.util.TreeSet;

public final class GenerateStudent {
    static Faker faker = new Faker();
    public static Student generate() {

        return new Student(
            GeneratePerson.socialSecurityNo(),
            GeneratePerson.firstName(),
            GeneratePerson.lastName(),
            GeneratePerson.birthDate(),
            GeneratePerson.nationality(),
            bookNumber()
        );
    }

    public static TreeSet<Student> generateAmount(int number) {
        TreeSet<Student> treeSet = new TreeSet<>();

        for (int i = 0; i < number; i++) {
            treeSet.add(generate());
        }

        return treeSet;
    }

    private static String bookNumber() {
        return faker.code().isbn10();
    }


}
