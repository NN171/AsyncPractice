package threads.buffer;

import java.util.concurrent.CountDownLatch;

public class PutThread extends Thread {

    private final BoundedBuffer<Integer> buffer;
    private final CountDownLatch previous;
//    private final CountDownLatch next;
    private final CountDownLatch end;

    public PutThread(BoundedBuffer<Integer> buffer, CountDownLatch previous, CountDownLatch end) {
        this.buffer = buffer;
        this.previous = previous;
//        this.next = next;
        this.end = end;
    }

    @Override
    public void run() {
//        for (int i = 0; i < 10; i++) {
            try {
                previous.await();
                buffer.take();
//                next.countDown();
                end.countDown();
            }
            catch (InterruptedException e) {
                System.err.println("Ошибка удаления");
            }
//        }
    }
}
