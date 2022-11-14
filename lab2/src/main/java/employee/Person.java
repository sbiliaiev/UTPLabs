package employee;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // the type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    private final String firstName; // backing field
    private final String lastName;
    private final LocalDate birthDate;

    protected Person(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.birthDate = LocalDate.parse(birthDate);
    }

    public String getFirstName() { // getter
        //
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public short getAge() {
        return (short) Period.between(LocalDate.now(), this.birthDate).getYears();
    }
}