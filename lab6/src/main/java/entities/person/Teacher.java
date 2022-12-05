package entities.person;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Teacher extends Person {
    private final Degree degree;
    private final LocalDate hireDate;

    public Teacher(String socialSecurityNo,
                   String firstName,
                   String lastName,
                   LocalDate birthDate,
                   Nationality nationality,
                   Degree degree,
                   LocalDate hireDate) {
        super(socialSecurityNo, firstName, lastName, birthDate, nationality);
        this.degree = degree;
        this.hireDate = hireDate;
    }

    public Degree getDegree() {
        return degree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public boolean equals(Object o) {
        Teacher t = (Teacher) o;
        return super.equals(o) && degree.equals(t.getDegree()) && hireDate.equals(t.getHireDate());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash *= 31;
        hash += Objects.hash(degree, hireDate);
        return hash;
    }

    @Override
    public int compareTo(Person o) {
        int res = super.compareTo(o);
        Teacher t = (Teacher) o;
        if (res == 0) {
            res = hireDate.compareTo(t.getHireDate());
        }
        if (res == 0) {
            res = degree.compareTo(t.getDegree());
        }

        return res;
    }
}
