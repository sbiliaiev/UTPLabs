package entities.person;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Student extends Person {
    private final String bookNumber;

    public Student(String socialSecurityNo,
                   String firstName,
                   String lastName,
                   LocalDate birthDate,
                   Nationality nationality,
                   String bookNumber) {
        super(socialSecurityNo, firstName, lastName, birthDate, nationality);
        this.bookNumber = bookNumber;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    @Override
    public boolean equals(Object o) {
        Student s = (Student) o;
        return super.equals(o) && bookNumber.equals(s.getBookNumber());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash *= 31;
        hash += Objects.hash(bookNumber);
        return hash;
    }

    @Override
    public int compareTo(Person o) {
        int res = super.compareTo(o);
        if (res == 0) {
            Student s = (Student) o;
            res = bookNumber.compareTo(s.getBookNumber());
        }

        return res;
    }
}
