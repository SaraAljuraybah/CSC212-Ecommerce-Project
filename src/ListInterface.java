package phase1;

public interface ListInterface<T> {
    void add(T item);
    T searchById(int id);
    int getSize();
}

