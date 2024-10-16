package threads.sync;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedBankAccountDemo {
    public static void main(String[] args) {
        SynchronizedBankAccount account = new SynchronizedBankAccount();
        Random random = new Random();
        Thread[] threads = new Thread[200_000];
        AtomicInteger finalSum = new AtomicInteger(1000);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                if (random.nextBoolean()) {
                    int sum = random.nextInt(100, 200);
                    finalSum.addAndGet(sum);
                    account.deposit(sum);
                }
                else {
                    int out = random.nextInt(50, 100);
                    finalSum.addAndGet(-out);
                    account.withdraw(out);
                }
            });

            threads[i].start();
        }
            // TODO: Создайте и запустите потоки, выполняющие случайные операции со счетом

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ожидаемый баланс: " + finalSum);
        System.out.println("Финальный баланс: " + account.getBalance());
    }
}
