package threads.sync;

public class SimpleCounter {
    private int count = 0;

    public synchronized void increment() {
        if (count < 500_000) {
            count++;
        }
    }

    public synchronized void decrement() {
        if (count > 0) {
            count--;
        }
    }

    public synchronized int getCount() {
        return count;
    }
}
