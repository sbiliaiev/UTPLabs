import java.util.List;

public class Container <T extends IAggregable<T, E> & IDeeplyCloneable<T>, E> implements IContainer<T, E> {

    private final List<T> elements;

    public Container(List<T> elements) throws Exception {
        if (elements == null) {
            throw new Exception("error");
        } else {
            this.elements = elements;
        }
    }

    @Override
    public List<T> elements() {
        return this.elements;
    }

    @Override
    public E aggregateAllElements() {
        E aggregated = null;
        for (T element : this.elements) {
            aggregated = element.aggregate(aggregated);
        }
        return aggregated;
    }

    @Override
    public T cloneElementAtIndex(int index) {
        return this.elements.get(index).deepClone();
    }
}
