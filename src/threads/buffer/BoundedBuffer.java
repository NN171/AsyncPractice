package threads.buffer;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size) {
        this.buffer = (T[]) new Object[size];
    }

    public synchronized void put(T item) {
        while (count == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

//        int counter = in;
        buffer[in++] = item;
        in %= buffer.length;
        count++;

        notifyAll();
//        print("Добавление: ", item, counter);
    }

    public synchronized T take() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        T element = buffer[out];
//        int counter = out;
        count--;
        buffer[out++] = null;
        out %= buffer.length;
        notifyAll();

//        print("Вывод: ", element, counter);
        return element;
    }

//    public synchronized void print(String action, T element, int counter) {
//        System.out.println(action + element + " " + counter);
//    }
}
