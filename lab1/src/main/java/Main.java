import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Container<Book, Integer> bookContainer = new Container<>(
            Arrays.asList(new Book(123), new Book(456), new Book(999))
        );

        System.out.println(bookContainer.elements());
        System.out.println(bookContainer.aggregateAllElements());
        System.out.println(bookContainer.cloneElementAtIndex(2));
        System.out.println(bookContainer.cloneElementAtIndex(2).pages());

        System.out.println("\n------------------------------------------------------\n");

        Container<Cat, String> catContainer = new Container<>(
            Arrays.asList(new Cat(1), new Cat(2), new Cat(3))
        );
        System.out.println(catContainer.elements());
        System.out.println(catContainer.aggregateAllElements());
        System.out.println(catContainer.cloneElementAtIndex(2));
        System.out.println(catContainer.cloneElementAtIndex(2).meow());

        System.out.println("\n------------------------------------------------------\n");

        Container<Item, Integer> itemContainer = new Container<>(
            Arrays.asList(new Item(23), new Item(56), new Item(98))
        );
        System.out.println(itemContainer.elements());
        System.out.println(itemContainer.aggregateAllElements());
        System.out.println(itemContainer.cloneElementAtIndex(2));
        System.out.println(itemContainer.cloneElementAtIndex(2).price());
    }
}
