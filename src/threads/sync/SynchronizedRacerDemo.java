package threads.sync;

import java.util.concurrent.CountDownLatch;

public class SynchronizedRacerDemo {
    public static void main(String[] args) throws InterruptedException {
        int racerCount = 3;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(racerCount);

        for (int i = 1; i <= racerCount; i++) {
            new Thread(new SynchronizedRacer("Гонщик " + i, startSignal, finishSignal)).start();
        }

        System.out.println("Гонка начинается!");
        startSignal.countDown(); // Старт гонки
        finishSignal.await();    // Ждем завершения всех гонщиков
        System.out.println("Все гонщики финишировали!");
    }
}
