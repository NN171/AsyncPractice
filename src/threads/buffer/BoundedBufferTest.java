package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 10000;
        CountDownLatch end = new CountDownLatch(length); //TODO add countdownLatch for threads sequence
        CountDownLatch previous = new CountDownLatch(1);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10, end);

        for (int i = 0; i < length; i++) {

            CountDownLatch next = new CountDownLatch(1);

            new Thread(new TakeThread(buffer, previous, next, end)).start();
            new Thread(new PutThread(buffer, previous, next, end)).start();
            previous = next;
        }

        previous.countDown();
        end.await();
    }
}
