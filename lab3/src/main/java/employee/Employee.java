package employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
public abstract class Employee extends Person {

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    // (assignment 03)
    // methods:
    // * salary is greater than given amount of money
    // * salary is less than given amount of money
    // * compare salary with other employee salary

    private BigDecimal salary;
    private final Manager manager;

    protected Employee(String firstName, String lastName, String birthDate, BigDecimal salary, Manager manager) {
        super(firstName, lastName, birthDate);
        this.salary = salary;
        this.manager = manager;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return this.manager;
    }

    // assignment 03

    public boolean isLessSalaryThan(BigDecimal givenSalary) {
        return salary.compareTo(givenSalary) < 0;
    }

    public boolean isGreaterSalaryThan(BigDecimal givenSalary) {
        return salary.compareTo(givenSalary) > 0;
    }

    public boolean compareSalaryWithEmployee(Employee givenEmployee) {
        return salary.compareTo(givenEmployee.getSalary()) > 0;
    }
}