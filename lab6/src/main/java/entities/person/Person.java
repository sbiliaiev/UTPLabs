package entities.person;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private final String socialSecurityNo;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Nationality nationality;

    public Person(String socialSecurityNo,
                  String firstName,
                  String lastName,
                  LocalDate birthDate,
                  Nationality nationality) {
        this.socialSecurityNo = socialSecurityNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public String getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return socialSecurityNo.equals(person.socialSecurityNo) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && birthDate.equals(person.birthDate) && nationality.equals(person.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurityNo, firstName, lastName, birthDate, nationality);
    }

    @Override
    public int compareTo(Person o) {
        Collator polishCollator = Collator.getInstance(NationalityLocaleMap.getLocaleByNationality(Nationality.POLISH));

//        return Comparator
//            .comparing(Person::getFirstName)
////            .thenComparing(Comparator.)
//            .thenComparing(Person::getLastName)
//            .compare(this, o)
//        ;

        int res = polishCollator.compare(firstName, o.getFirstName());
        if (res == 0) {
            res = polishCollator.compare(lastName, o.getLastName());
        }
        if (res == 0) {
            res = nationality.compareTo(o.getNationality());
        }
        if (res == 0) {
            res = socialSecurityNo.compareTo(o.getSocialSecurityNo());
        }
        if (res == 0) {
            res = birthDate.compareTo(o.getBirthDate());
        }

        return res;
    }
}
