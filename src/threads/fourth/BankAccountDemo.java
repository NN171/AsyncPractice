package threads.fourth;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Random random = new Random();
        Thread[] threads = new Thread[50_000];
        AtomicInteger finalSum = new AtomicInteger(1000);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                if (random.nextBoolean()) {
//                    int sum = random.nextInt(100, 200);
                    account.deposit(random.nextInt(100, 200));
                } else {
//                    int out = random.nextInt(50, 100);
                    account.withdraw(random.nextInt(50, 100));
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
        // TODO: Дождитесь завершения всех операций

        System.out.println("Финальный баланс: " + account.getBalance());
    }
}
