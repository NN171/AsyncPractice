package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 10;
        CountDownLatch end = new CountDownLatch(length); //TODO add countdownLatch for threads sequence
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10, end);

        for (int i = 0; i < length; i++) {

            CountDownLatch next = new CountDownLatch(1);

            new Thread(new TakeThread(buffer, next, end)).start();
            new Thread(new PutThread(buffer, next, end)).start();
        }

        end.await();
    }
}
