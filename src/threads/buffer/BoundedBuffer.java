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

    public BoundedBuffer() {
        this(5);
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
        notifyAll();

        System.out.println("Добавлен: " + getBufferCount());
    }

    public synchronized void take() {
        while (count == 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }


        count--;
        out %= buffer.length;
        notifyAll();

        System.out.println("Удален: " + buffer[out++]);
        //return out;
    }

//    public synchronized void setCount(int amount) {
//        this.count += amount;
//    }

    public int getBufferCount() {
        return this.count;
    }
}
