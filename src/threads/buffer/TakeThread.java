package threads.buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TakeThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch start;
    private final CountDownLatch end;
    private final Random random;

    public TakeThread(BoundedBuffer<Integer> buffer, CountDownLatch start, CountDownLatch end) {
        this.buffer = buffer;
        this.start = start;
        this.end = end;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            start.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buffer.put(random.nextInt(1, 100));
        end.countDown();
    }
}
