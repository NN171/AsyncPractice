package threads.buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {

        int length = 10_000;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(length*2);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        Random random = new Random();

        for (int i = 0; i < length; i++) {

            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                buffer.put(random.nextInt(1, 100));
                end.countDown();
            }).start();

            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(buffer.take());
                end.countDown();
            }).start();
        }

        start.countDown();
        end.await();
    }
}
