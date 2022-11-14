import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import payroll.PayrollEntry;

public final class HumanResourcesStatistics {

	/*
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee
	 */

    public static BigDecimal calculateBonus(Employee e) {
        return e instanceof Worker ? (((Worker) e).getBonus()) : BigDecimal.ZERO;
    }

    public static List<PayrollEntry> payroll(List<Employee> employees) {
        return employees.stream().map(e -> new PayrollEntry(e, e.getSalary(), calculateBonus(e))).collect(Collectors.toList());
    }

    // payroll for all subordinates
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        return payroll(manager.getSubordinates());
    }

    public static List<PayrollEntry> allSubordinatesPayroll(Manager manager) {
        return payroll(manager.getAllSubordinates());
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
        return employees.stream().map(HumanResourcesStatistics::calculateBonus).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Employee longestSeniority(List<Employee> employees) {
        Function<Worker, Integer> calculateSeniority = w -> Period.between(LocalDate.now(), w.getEmploymentDate()).getDays();
        return employees
            .stream()
//            .filter(e -> e instanceof Worker)
            .filter(Worker.class::isInstance)
            .map(Worker.class::cast)
            .max(Comparator.comparing(calculateSeniority))
//            .max(Comparator.comparing(e -> Period.between(LocalDate.now(), ((Worker) e).getEmploymentDate()).getDays()))
            .get();
    }

    public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        return employees.stream().map(Employee::getSalary).max(Comparator.naturalOrder()).orElse(null);
    }

    public static BigDecimal highestSalaryIncludingBonus(List<Employee> employees) {
        return employees
            .stream()
            .map(e -> new PayrollEntry(e, e.getSalary(), calculateBonus(e)))
            .map(PayrollEntry::getSalaryPlusBonus)
            .max(Comparator.naturalOrder())
            .orElse(null);
    }

    public static List<Employee> surnameBeginsWithA(Manager manager) {
        return manager.getSubordinates().stream().filter(emp -> emp.getLastName().startsWith("A")).collect(Collectors.toList());
    }

    public static List<Employee> earnMoreThan1000(List<Employee> employees) {
        return employees
            .stream()
            .filter(e -> new PayrollEntry(e, e.getSalary(), calculateBonus(e))
                .getSalaryPlusBonus()
                .compareTo(BigDecimal.valueOf(1000)) > 0)
            .collect(Collectors.toList());
    }

    /// ...
    // rest of the methods specified in the assignment description

    /**
     * samples for functional processing in Java
     */
    public static List<Short> getAges(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        List<Short> ages = employees
            .stream()
            .map(emp -> emp.getAge())
            .collect(Collectors.toList());
        return ages;
    }

    public static void printAges(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        employees //
            .stream() //
            .map(emp -> (int) emp.getAge()) //
            .forEach(age -> System.out.print(age + ", "));
    }

    //
    // average age for the Employees whose first name starts with 'A' and they are older than 20
    public static short getAverageAgeInline(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
            .stream() //
            .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
            .map(emp -> (int) emp.getAge()) //
            .reduce(0, //
                (total, age) -> total + age);

        long filteredEmployeesCount = employees //
            .stream() //
            .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
            .count();

        return (short) (employeeTotalAge / filteredEmployeesCount);
    }

    public static short getAverageAgeMethodReference(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
            .stream() //
            .map(emp -> (int) emp.getAge()) //
            .reduce(0, HumanResourcesStatistics::totalAge);
        return (short) (employeeTotalAge / employees.size());
    }

    public static short getMaxAgeInline(List<Employee> employees) {
        short employeeMaxAge = employees //
            .stream() //
            .map(emp -> emp.getAge()) //
            .reduce((short) 0, //
                (maxAge, age) -> {
                    if (maxAge < age) {
                        return age;
                    } else {
                        return maxAge;
                    }
                });
        return employeeMaxAge;
    }

    public static short getMaxAgeMethodReference(List<Employee> employees) {
        short employeeMaxAge = employees //
            .stream() //
            .map(emp -> emp.getAge()) //
            .reduce((short) 0, HumanResourcesStatistics::maxAge);
        return employeeMaxAge;
    }

    private static int totalAge(int totalAge, int age) {
        //
        return totalAge + age;
    }

    private static short maxAge(short maxAge, short age) {
        if (maxAge < age) {
            return age;
        } else {
            return maxAge;
        }
    }

    // ASSIGNMENT 03

    // The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
    // In addition the list of arguments may be augmented with parameters required for the given feature.

    // * search for Employees older than given employee and earning less than him
    public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
        return allEmployees
            .stream()
            .filter(e -> e.isOlder(employee))
            .filter(e -> e.isLessSalaryThan(employee.getSalary()))
            .collect(Collectors.toList());
    }

    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
//        BiFunction<Employee, Float, Employee> increaseEmployeeSalary = (e, inc) -> {
//            e.setSalary(BigDecimal.valueOf(e.getSalary().intValue() * inc));
//            return e;
//        };
        return allEmployees
            .stream()
            .filter( Trainee.class::isInstance)
            .map(Trainee.class::cast)
            .filter(e -> e.isPracticeLongerThan(daysCount))
//            .peek(e -> e.setSalary(BigDecimal.valueOf(e.getSalary().intValue() * 1.05)))
            .map(e -> e.toBuilder().salary(BigDecimal.valueOf(e.getSalary().intValue() * 1.05)).build())
            .collect(Collectors.toList());
    }

    // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        return allEmployees
            .stream()
            .map( Worker.class::cast)
            .filter(e -> e.isSeniorityInMonthsLongerThan(monthCount))
            .map(e -> {
                if (e.getBonus().intValue() < 300) {
                    return e.toBuilder().bonus(BigDecimal.valueOf(300)).build();
                }
                return e;
            })
            .collect(Collectors.toList());
    }

    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        return allEmployees
            .stream()
            .map( Worker.class::cast)
            .filter(e -> e.getSeniority().getYears() >= 1)
            .filter(e -> e.getSeniority().getYears() <= 3)
            .map(e -> e.toBuilder().salary(BigDecimal.valueOf(e.getSalary().intValue() * 1.1)).build())
            .collect(Collectors.toList());
    }

    // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
        return allEmployees
            .stream()
            .map( Worker.class::cast)
            .filter(e -> e.isSeniorityInMonthsLongerThan(e.getSeniority().getMonths()))
            .filter(e -> e.isLessSalaryThan(e.getSalary()))
            .map(e -> e.toBuilder().salary(e.getSalary()).build())
            .collect(Collectors.toList());
    }

    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        return allEmployees
            .stream()
            .map( Worker.class::cast)
            .filter(e -> e.getSeniority().getYears() >= 2)
            .filter(e -> e.getSeniority().getYears() <= 4)
            .filter(e -> e.getAge() > age)
            .collect(Collectors.toList());
    }
}