package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 10000;
        CountDownLatch end = new CountDownLatch(length*2); //TODO add countdownLatch for threads sequence
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch previousTake = start;
        CountDownLatch previousPut = start;
        Thread[] threads = new Thread[length];
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10, end);

        for (int i = 0; i < length; i++) {

            new Thread(new TakeThread(buffer, start, end)).start();
            new Thread(new PutThread(buffer, start, end)).start();
        }

        start.countDown();

        end.await();
    }
}
