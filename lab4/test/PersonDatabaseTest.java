import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDatabaseTest {
    PersonDatabase database;

    @BeforeEach
    void setUp() throws FileNotFoundException, ParseException {
        database = new PersonDatabase(
            InputParser.parse(
                new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/test/data/person-data.txt")
            )
        );
    }

    @Test
    void sortedByFirstName() {
        List<Person> list = database.sortedByFirstName();
        assertEquals("Alex", list.get(0).getFirstName());
        assertEquals("Mark", list.get(list.size() - 1).getFirstName());
    }

    @Test
    void sortedBySurnameFirstNameAndBirthdate() {
        List<Person> list = database.sortedBySurnameFirstNameAndBirthdate();
        assertEquals("Mark", list.get(0).getFirstName());
        assertEquals("Brown", list.get(0).getSurname());
        assertEquals("John", list.get(list.size() - 1).getFirstName());
        assertEquals("Wick", list.get(list.size() - 1).getSurname());
    }

    @Test
    void sortedByBirthdate() {
        List<Person> list = database.sortedByBirthdate();
        assertEquals("Mark", list.get(0).getFirstName());
        assertEquals("Kratos", list.get(list.size() - 1).getFirstName());
    }

    @Test
    void bornOnDay() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date1 = formatter.parse("1989-03-23");
        List<Person> list = database.bornOnDay(date1);
        assertEquals(3, list.size());

        Date date2 = formatter.parse("1991-03-23");
        list = database.bornOnDay(date2);
        assertEquals("Kratos", list.get(0).getFirstName());

        Date date3 = formatter.parse("1978-03-23");
        list = database.bornOnDay(date3);
        assertEquals("John", list.get(0).getFirstName());
    }
}