public final class Book implements IAggregable<Book, Integer>, IDeeplyCloneable<Book> {

    private final int pages;

    public Book(int pages) {
        this.pages = pages;
    }

    public int pages() {
        return this.pages;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if (intermediateResult == null) {
            return this.pages;
        }

        return this.pages + intermediateResult;
    }

    @Override
    public Book deepClone() {
        return new Book(this.pages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return this.pages == book.pages();
    }

    @Override
    public String toString() {
        return "Book{" +
            "pages=" + this.pages +
            '}';
    }
}
