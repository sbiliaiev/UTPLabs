package func;

import employee.Employee;
import employee.Person;
import employee.Worker;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ComparisonFunctions {

    /* A set of non-generic bi-functions */
    public static BiFunction<Person, Person, Integer> personAgeDifference = (p1, p2) -> p1.getAge() - p2.getAge();
    public static BiFunction<Person, Person, Boolean> personIsYounger = (p1, p2) -> personAgeDifference.apply(p1, p2) < 0;

    public static BiFunction<Person, Person, Boolean> personIsOlder = (p1, p2) -> personAgeDifference.apply(p1, p2) > 0;

    /* A set of generic bi-functions */
    public static BiFunction<Employee, Employee, Integer> nonPersonSalaryDifferenceGeneric =
        diffFunction(Employee::getSalary, (v1, v2) -> v1 - v2);

    public static BiFunction<Person, Person, Integer> personAgeDifferenceGeneric =
        diffFunction(Person::getAge, (v1, v2) -> v1 - v2);

    public static BiFunction<Worker, Worker, Boolean> nonPersonHasLargerSalary =
        (n1, n2) -> nonPersonSalaryDifferenceGeneric.apply(n1, n2) > 0;

    public static <T, R extends Number> BiFunction<T, T, R> diffFunction(
        Function<T, R> extractor,
        BiFunction<R, R, R> processor
    ) {
        return (p1, p2) -> processor.apply(extractor.apply(p1), extractor.apply(p2));
    }
}
