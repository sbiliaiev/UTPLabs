package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Trainee extends Employee {

    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)

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
}