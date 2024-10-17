package threads.buffer;

import java.util.Random;

public class BoundedBufferTest {
    public static void main(String[] args) {

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(3);
        Thread[] threads = new Thread[20];
        Random random = new Random();

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(() -> {
                if (random.nextBoolean()) {
                    buffer.put(random.nextInt(1, 10));
                }
                else {
                    buffer.take();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buffer.getBufferCount());
    }
}
