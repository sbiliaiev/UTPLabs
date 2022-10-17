public final class Item implements IAggregable<Item, Integer>, IDeeplyCloneable<Item> {

    private final int price;

    public Item(int price) {
        this.price = price;
    }

    public int price() {
        return this.price;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if (intermediateResult == null) {
            return this.price;
        }

        return this.price + intermediateResult;
    }

    @Override
    public Item deepClone() {
        return new Item(this.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return this.price == item.price();
    }

    @Override
    public String toString() {
        return "Item{" +
            "price=" + this.price +
            '}';
    }
}
