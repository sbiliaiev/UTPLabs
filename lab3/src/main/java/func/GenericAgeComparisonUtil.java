package func;

import java.util.function.Function;

/**
 * Generic util
 */
public class GenericAgeComparisonUtil {

    public static <T> boolean isSameAge(T p1, T p2, Function<T, Integer> getAge) {
        return ageDiff(p1, p2, getAge) == 0;
    }

    public static <T> boolean isOlder(T p1, T p2, Function<T, Integer> getAge) {
        return ageDiff(p1, p2, getAge) > 0;
    }

    public static <T> boolean isYounger(T p1, T p2, Function<T, Integer> getAge) {
        return ageDiff(p1, p2, getAge) < 0;
    }

    public static <T> int ageDiff(T p1, T p2, Function<T, Integer> getAge) {
        return getAge.apply(p1) - getAge.apply(p2);
    }
}
