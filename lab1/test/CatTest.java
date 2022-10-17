import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class CatTest {

    private static final int MEOWS = 123;
    private static final String AGGREGATED = "Meow! Meow!";
    private static Cat cat;

    @BeforeAll
    static void beforeAll() {
        cat = new Cat(MEOWS);
    }

    @Test
    void meows() {
        assertEquals("Meow! ".repeat(Math.max(0, MEOWS)), cat.meow());
    }

    @Test
    void aggregate() {
        assertEquals(cat.meow(), cat.aggregate(null));
        assertEquals(cat.meow() + AGGREGATED, cat.aggregate(AGGREGATED));
    }

    @Test
    void deepClone() {
        assertEquals(cat, cat.deepClone());
    }
}