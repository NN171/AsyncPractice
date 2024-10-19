package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 1000;
        CountDownLatch end = new CountDownLatch(length*2);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10, end);

        for (int i = 0; i < length; i++) {

            new Thread(new TakeThread(buffer, end)).start();
            new Thread(new PutThread(buffer, end)).start();
        }

        end.await();
    }
}
