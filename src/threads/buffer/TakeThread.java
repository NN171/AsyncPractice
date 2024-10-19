package threads.buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TakeThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch end;
    private final Random random;

    public TakeThread(BoundedBuffer<Integer> buffer, CountDownLatch end) {
        this.buffer = buffer;
        this.end = end;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            buffer.put(random.nextInt(1, 100));
            end.countDown();
        }
    }
}
