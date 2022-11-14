package func;

import java.util.function.BiFunction;

public class ItemComparator {

    public static <T, R> R compare(T t1, T t2, BiFunction<T, T, R> comparator) {
        return comparator.apply(t1, t2);
    }
}
