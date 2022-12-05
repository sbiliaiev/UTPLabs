package data;

import com.github.javafaker.Faker;
import entities.person.Nationality;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;

public class GeneratePerson {

    static Faker faker = new Faker();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String socialSecurityNo() {
        return faker.code().imei().toString();
    }

    public static String firstName() {
        return faker.name().firstName();
    }

    public static String lastName() {
        return faker.name().lastName();
    }

    public static LocalDate birthDate() {
        return LocalDate.parse(sdf.format(faker.date().birthday()));
    }

    public static Nationality nationality() {
        return Nationality.values()[new Random().nextInt(Nationality.values().length)];
    }
}
