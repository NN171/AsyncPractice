package threads.buffer;

public class BoundedBuffer<T> {
    private T[] array;
    private int count = 0;
    private int length;

    public BoundedBuffer(int length) {
        this.length = length;
        this.array = (T[]) new Object[length];
    }

    public void put(T object) {
        if (count == length) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        array[count++] = object;
        if (count != 0) {
            notifyAll();
        }
    }

    public T take() {
        if (count == 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        array[count] = null;

        if (count == length) {
            notifyAll();
        }

        return array[count--];
    }
}
