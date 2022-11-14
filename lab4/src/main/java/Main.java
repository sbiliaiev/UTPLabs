import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        List<Person> list = InputParser.parse(new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/src/main/java/data/person-data.txt"));
        PersonDatabase database = new PersonDatabase(list);
//        System.out.println("FIstname Comparator");
//        System.out.println(database.sortedByFirstName());
//        System.out.println("Birtname Comparator");
//        System.out.println(database.sortedByBirthdate());
//        System.out.println("Natural order");
//        System.out.println(database.sortedBySurnameFirstNameAndBirthdate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse("1989-03-23");
        System.out.println("By date");
        System.out.println(database.bornOnDay(date));
    }
}
