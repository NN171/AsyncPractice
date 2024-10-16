package threads.sync;

import java.util.concurrent.CountDownLatch;

public class SynchronizedRacer implements Runnable {
    private String name;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    public SynchronizedRacer(String name, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.name = name;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); // Ждем сигнала старта
            long startTime = System.nanoTime();
            System.out.println(name + " начал гонку");
            for (int i = 0; i < 100_000; i++) {}
            System.out.println(name + " финишировал: " + (System.nanoTime() - startTime));
            finishSignal.countDown(); // Сигнализируем о завершении

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
