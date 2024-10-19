package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class PutThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch start;
    private final CountDownLatch end;

    public PutThread(BoundedBuffer<Integer> buffer, CountDownLatch start, CountDownLatch end) {
        this.buffer = buffer;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            start.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(buffer.take());
        end.countDown();
    }
}
