package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    private final LocalDate employmentDate;
    private final BigDecimal bonus;

    public Worker(String firstName,
                  String lastName,
                  String birthDate,
                  BigDecimal salary,
                  Manager manager,
                  String employmentDate,
                  BigDecimal bonus) {
        //
        super(firstName, lastName, birthDate, salary, manager);
        this.employmentDate = LocalDate.parse(employmentDate);
        this.bonus = bonus;
    }

    public LocalDate getEmploymentDate() {
        return this.employmentDate;
    }

    public BigDecimal getBonus() {
        return this.bonus;
    }

    @Override
    public String toString() {
        return "[Worker " + getFirstName() + " -> " + getManager().getFirstName() + "]";
    }
}