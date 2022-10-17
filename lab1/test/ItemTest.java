import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    private static final int PRICE = 123;
    private static final int AGGREGATED = 10;
    private static Item item;

    @BeforeAll
    static void beforeAll() {
        item = new Item(PRICE);
    }

    @Test
    void price() {
        assertEquals(PRICE, item.price());
    }

    @Test
    void aggregate() {
        assertEquals(PRICE, item.aggregate(null));
        assertEquals(PRICE + AGGREGATED, item.aggregate(AGGREGATED));
    }

    @Test
    void deepClone() {
        assertEquals(item, item.deepClone());
    }
}