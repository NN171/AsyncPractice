package threads.buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TakeThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch next;
    private final CountDownLatch end;
    private final Random random;

    public TakeThread(BoundedBuffer<Integer> buffer, CountDownLatch next, CountDownLatch end) {
        this.buffer = buffer;
        this.next = next;
        this.end = end;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                next.await();
                buffer.put(random.nextInt(1, 50));
                next.countDown();
                end.countDown();
            }
            catch (InterruptedException e) {
                System.err.println("Ошибка добавления");
            }
        }
    }
}
