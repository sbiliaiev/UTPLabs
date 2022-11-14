package employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)

    private final List<Employee> subordinates;

    private final Manager manager;

    public Manager(String firstName,
                   String lastName,
                   String birthDate,
                   BigDecimal salary,
                   Manager manager,
                   String employmentDate,
                   BigDecimal bonus) {
        super(firstName, lastName, birthDate, salary, manager, employmentDate, bonus);
        this.subordinates = new ArrayList<>();
        this.manager = manager;
    }

    public boolean isDirector() {
        return manager == null;
    }

    public void addSubordinate(Employee employee) {
        if (employee instanceof Manager && getManager() != null) {
            throw new IllegalArgumentException("Trying to register a manager subordinate for a manager");
        }

        if (employee.getManager() != this) {
            throw new IllegalArgumentException("Trying to register illegal employee");
        }

        subordinates.add(employee);
    }

    public List<Employee> getAllSubordinates() {
        final List<Employee> result = new ArrayList<>();
        result.addAll(subordinates);

        for (Employee s : subordinates) {
            if (s instanceof Manager) {
                result.addAll(((Manager) s).getAllSubordinates());
            }
        }
        return result;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public String toString() {
        final String title = isDirector() ? "Director" : "Manager";
        final String managerName = getManager() != null ? getManager().getFirstName() : "no manager";
        return "[" + title + " " + getFirstName() + " -> " + managerName + "]";
    }
}