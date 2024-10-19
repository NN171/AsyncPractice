package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 10_000;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(length*2);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);

        for (int i = 0; i < length; i++) {

            new Thread(new TakeThread(buffer, start, end)).start();
            new Thread(new PutThread(buffer, start, end)).start();
        }

        start.countDown();
        end.await();
    }
}
