package employee;

import java.util.function.Function;

public class Difference {
    public static <T> boolean isLess(Function<T, Short> calculator, T o1, T o2) {
        return calculator.apply(o1) < calculator.apply(o2);
    }
}
