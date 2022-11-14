package employee;

import func.AgeComparisonUtil;
import func.ComparisonFunctions;
import func.GenericAgeComparisonUtil;
import func.ItemComparator;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.BiFunction;
import lombok.Builder;

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

    // (assignment 03)
    // methods:
    // * is older than other person
    // * is younger than other person
    // * compare age with other person's age

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

    //assignment 03

//    public boolean isOlder(Person person) { return getAge() > person.getAge(); }
    public boolean isOlder(Person person) { return ageDiff(person) > 0; }

    public boolean isYounger(Person person) { return ageDiff(person) < 0; }

    public int ageDiff(Person person) { return getAge() - person.getAge(); }

    // age comparison via difference package

    public boolean isYoungerDifference(Person person) {
        return Difference.isLess(Person::getAge, this, person);
    }

    public boolean isOlderDifference(Person person) {
        return !isYounger(person);
    }

    // age comparison via utils package

    public boolean isYoungerUtil(Person other) {
        return AgeComparisonUtil.isYounger(this, other);
    }

//    public boolean isYoungerGeneric(Person other) {
//        return GenericAgeComparisonUtil.isYounger(this, other, Person::getAge);
//    }

    /**
     * An example of using a comparator with lambda bifunction.
     */
    public boolean isYoungerFunction(Person other) {
        return ItemComparator.compare(this, other, (p1, p2) -> p1.getAge() < p2.getAge());
        // same as `return getAge() - other.getAge()` but with extra steps
    }

    /**
     * An example of using a raw bifunction.
     */
    public boolean isYoungerRawFunction(Person other) {
        return ((BiFunction<Person, Person, Boolean>) (p1, p2) -> p1.getAge() < p2.getAge()).apply(this, other);
    }

    /**
     * An example of using an extracted bifunction.
     */
    public boolean isYoungerExtractedFunction(Person other) {
        BiFunction<Person, Person, Boolean> comparisonFunction = (p1, p2) -> p1.getAge() < p2.getAge();
        return comparisonFunction.apply(this, other);
    }

    /**
     * An example of using a stored bifunction.
     */
    public boolean isYoungerStoredFunction(Person other) {
        return ComparisonFunctions.personIsYounger.apply(this, other);
    }

    /**
     * An example of using a generic diff bifunction
     */
//    public boolean isYoungerLambda(Person other) {
//        return ComparisonFunctions.diffFunction(Person::getAge, (v1, v2) -> v1 - v2).apply(this, other) < 0;
//    }
}