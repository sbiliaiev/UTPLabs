package entities;

import java.text.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @BeforeAll
    static void setUp() {
    }

    @Test
    void constructor_correctDate_parsesCorrect() throws ParseException {
        Person p = new Person("John", "Wick", "1991-01-01");
        assertEquals("John", p.getFirstName());
        assertEquals("Wick", p.getSurname());
    }

    @Test
    void constructor_badDate_thowsError() {
        Exception exception = assertThrows(ParseException.class, () -> {
            Person p = new Person("John", "Wick", "199fdsf122-01-01");
        });
    }

    @Test
    void compareTo_byUsing_surName() throws ParseException {
        Person p1 = new Person("Alex", "Webb", "1991-03-21");
        Person p2 = new Person("Mark", "Shatner", "1991-03-21");
        assertTrue(p1.compareTo(p2) > 0);
    }

    @Test
    void compareTo_byUsing_firstName() throws ParseException {
        Person p1 = new Person("Alex", "Webb", "1991-03-21");
        Person p2 = new Person("Mark", "Webb", "1991-03-21");
        assertTrue(p1.compareTo(p2) < 0);
    }

    @Test
    void compareTo_byUsing_birthDate() throws ParseException {
        Person p1 = new Person("Alex", "Webb", "1990-03-21");
        Person p2 = new Person("Alex", "Webb", "1991-03-21");
        assertTrue(p1.compareTo(p2) < 0);
    }

    @Test
    void compareTo_equals() throws ParseException {
        Person p1 = new Person("Alex", "Webb", "1991-03-21");
        Person p2 = new Person("Alex", "Webb", "1991-03-21");
        assertEquals(0, p1.compareTo(p2));
    }
}