import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ContainerTest {

    private static Container<Book, Integer> bookContainer;
    private static final Book book = new Book(123);
    private static final List<Book> bookList = Arrays.asList(book, new Book(456), new Book(999));
    private static Container<Cat, String> catContainer;
    private static final Cat cat = new Cat(1);
    private static final List<Cat> catList = Arrays.asList(cat, new Cat(2), new Cat(3));
    private static Container<Item, Integer> itemContainer;
    private static final Item item = new Item(23);
    private static final List<Item> itemList = Arrays.asList(item, new Item(56), new Item(98));

    @BeforeAll
    static void beforeAll() {
        bookContainer = new Container<>(bookList);
        catContainer = new Container<>(catList);
        itemContainer = new Container<>(itemList);
    }

    @Test
    void elements() {
        assertEquals(bookList, bookContainer.elements());
        assertEquals(catList, catContainer.elements());
        assertEquals(itemList, itemContainer.elements());
    }

    @Test
    void aggregateAllElements() {
        assertEquals(bookList.stream().map(Book::pages).reduce(Integer::sum).orElse(0), bookContainer.aggregateAllElements());
        assertEquals(catList.stream().map(Cat::meow).reduce(String::concat).orElse(""), catContainer.aggregateAllElements());
        assertEquals(itemList.stream().map(Item::price).reduce(Integer::sum).orElse(0), itemContainer.aggregateAllElements());
    }

    @Test
    void cloneElementAtIndex() {
        assertEquals(book, bookContainer.cloneElementAtIndex(0));
        assertEquals(cat, catContainer.cloneElementAtIndex(0));
        assertEquals(item, itemContainer.cloneElementAtIndex(0));
    }
}