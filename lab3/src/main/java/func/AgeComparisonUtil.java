package func;

import employee.Person;

/**
 * Just a regular util, no func.
 */
public class AgeComparisonUtil {

    public static boolean isSameAge(Person p1, Person p2) {
        return ageDiff(p1, p2) == 0;
    }

    public static boolean isOlder(Person p1, Person p2) {
        return ageDiff(p1, p2) > 0;
    }

    public static boolean isYounger(Person p1, Person p2) {
        return ageDiff(p1, p2) < 0;
    }

    public static int ageDiff(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}
