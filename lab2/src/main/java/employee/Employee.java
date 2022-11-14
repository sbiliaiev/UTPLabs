package employee;

import java.math.BigDecimal;

public abstract class Employee extends Person {

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    private final BigDecimal salary;
    private final Manager manager;

    protected Employee(String firstName, String lastName, String birthDate, BigDecimal salary, Manager manager) {
        super(firstName, lastName, birthDate);
        this.salary = salary;
        this.manager = manager;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public Manager getManager() {
        return this.manager;
    }
}