package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
public class Trainee extends Employee {

    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)

    // (assignment 03)
    // * practice length is shorter than given number of days
    // * practice length is longer than given number of days

    private final LocalDate apprenticeshipStartDate;
    private final int apprenticeshipLength;

    public Trainee(String firstName,
                      String lastName,
                      String birthDate,
                      BigDecimal salary,
                      Manager manager,
                      String apprenticeshipStartDate,
                      int apprenticeshipLength) {
        super(firstName, lastName, birthDate, salary, manager);
        this.apprenticeshipStartDate = LocalDate.parse(apprenticeshipStartDate);
        this.apprenticeshipLength = apprenticeshipLength;
    }

    @Override
    public String toString() {
        return "[Trainee " + getFirstName() + " -> " + getManager().getFirstName() + "]";
    }

    // assignment 03

    public boolean isPracticeShorterThan(int days) {
        return apprenticeshipLength < days;
    }

    public boolean isPracticeLongerThan(int days) {
        return apprenticeshipLength > days;
    }
}