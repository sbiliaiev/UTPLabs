package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus

    // (assignment 03)
    // attributes:
    // * has bonus
    //
    // methods:
    // * seniority is longer than given number of years (seniority - sta¿)
    // * seniority is longer than given number of months
    // * has bonus greater than given amount of money

    private final LocalDate employmentDate;
    private BigDecimal bonus;

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

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "[Worker " + getFirstName() + " -> " + getManager().getFirstName() + "]";
    }


    // assignment 03
    public boolean hasBonus() {
        return bonus.compareTo(new BigDecimal('0')) > 0;
    }

    public Period getSeniority() {
        return Period.between(LocalDate.now(), employmentDate);
    }

    public boolean isSeniorityInYearsLongerThan(int yearsAmount) {
        return getSeniority().getYears() > yearsAmount;
    }

    public boolean isSeniorityInMonthsLongerThan(int monthsAmount) {
        return getSeniority().getMonths() > monthsAmount;
    }

    public boolean hasBonusGreaterThan(BigDecimal bonusToCompare) {
        return bonus.compareTo(bonusToCompare) > 0;
    }
}