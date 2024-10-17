package threads.buffer;

public class BoundedBuffer<T> {
    private T[] array;
    private volatile int count = 0;
    private int length;
    private T in;
    private T out;

    public BoundedBuffer(int length) {
        this.length = length;
        this.array = (T[]) new Object[length];
        this.in = null;
        this.out = null;
    }

    public BoundedBuffer() {
        this(5);
    }

    public synchronized void put(T object) {
        while (count == length) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        in = object;
        array[count++] = in;
        notifyAll();

        System.out.println("Добавлен: " + count);
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

        out = array[--count];
        array[count] = null;
        notifyAll();

        System.out.println("Удален: " + count);
        //return out;
    }

//    public synchronized void setCount(int amount) {
//        this.count += amount;
//    }

    public int getBufferCount() {
        return this.count;
    }
}
