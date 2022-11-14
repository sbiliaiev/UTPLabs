package comparators;

import entities.Person;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstNameComparatorTest {

    static FirstNameComparator comparator;
    @BeforeAll
    static void setUp() {
        comparator = new FirstNameComparator();
    }

    @Test
    void compare_isEqual() throws ParseException {
        Person p1 = new Person("John", "Wick", "1991-01-01");
        Person p2 = new Person("John", "Pitt", "1993-01-01");
        assertEquals(0, comparator.compare(p1, p2));
        assertEquals(0, comparator.compare(p2, p1));
    }

    @Test
    void compare_lessThan() throws ParseException {
        Person p1 = new Person("Alex", "Wick", "1991-01-01");
        Person p2 = new Person("John", "Pitt", "1993-01-01");
        assertTrue(comparator.compare(p1, p2) < 0);
    }

    @Test
    void compare_greaterThan() throws ParseException {
        Person p1 = new Person("Alex", "Wick", "1991-01-01");
        Person p2 = new Person("John", "Pitt", "1993-01-01");
        assertTrue(comparator.compare(p2, p1) > 0);
    }
}