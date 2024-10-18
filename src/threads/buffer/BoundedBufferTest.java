package threads.buffer;

import java.util.Random;

public class BoundedBufferTest {
    public static void main(String[] args) {

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>();
        Thread[] putThreads = new Thread[100000];
        Thread[] takeThreads = new Thread[100000]; // TODO add CountDownLatch
        Random random = new Random();

        for (int i = 0; i < putThreads.length; i++) {

            putThreads[i] = new Thread(() ->
                    buffer.put(random.nextInt(1, 10))
            );

            takeThreads[i] = new Thread(() ->
                    buffer.take()
            );

            putThreads[i].start();
            takeThreads[i].start();
        }

        try {
            for (int i = 0; i < putThreads.length; i++) {
                putThreads[i].join();
                takeThreads[i].join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buffer.getBufferCount());
    }
}
