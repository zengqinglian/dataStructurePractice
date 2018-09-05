package eric.zeng.pool.fixedsize;

public interface Pool<T> {
    T getItem() throws InterruptedException;

    void returnItem(T item);
}
