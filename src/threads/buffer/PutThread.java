package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class PutThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch end;

    public PutThread(BoundedBuffer<Integer> buffer, CountDownLatch end) {
        this.buffer = buffer;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(buffer.take());
            end.countDown();
        }
    }
}
