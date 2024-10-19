package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    private CountDownLatch end;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size, CountDownLatch end) {
        this.buffer = (T[]) new Object[size];
        this.end = end;
    }

    public synchronized void put(T item) {
        while (count == buffer.length) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        buffer[in++] = item;
        in %= buffer.length;
        count++;

        System.out.println("Добавлен: " + this.count + " " + item);
        notifyAll();
    }

    public synchronized T take() {
        while (count == 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        T element = buffer[out];
        count--;
        buffer[out++] = null;
        out %= buffer.length;
        notifyAll();

        return element;
    }
}
