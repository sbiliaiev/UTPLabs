import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class BookTest {

    private static final int PAGES = 123;
    private static final int AGGREGATED = 10;
    private static Book book;

    @BeforeAll
    static void beforeAll() {
        book = new Book(PAGES);
    }

    @Test
    void pages() {
        assertEquals(PAGES, book.pages());
    }

    @Test
    void aggregate() {
        assertEquals(PAGES, book.aggregate(null));
        assertEquals(PAGES + AGGREGATED, book.aggregate(AGGREGATED));
    }

    @Test
    void deepClone() {
        assertEquals(book, book.deepClone());
    }
}