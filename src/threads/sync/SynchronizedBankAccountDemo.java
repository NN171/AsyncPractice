package threads.sync;

import java.util.Random;

public class SynchronizedBankAccountDemo {
    public static void main(String[] args) {
        SynchronizedBankAccount account = new SynchronizedBankAccount();
        Random random = new Random();
        Thread[] deposits = new Thread[10];
        Thread[] withdraws = new Thread[10];

        for (int i = 0; i < 10; i++) {
            deposits[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        int sum = random.nextInt(100, 1000);

                        account.deposit(sum);
                    }
                }
            });

            Thread withdraw = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        int out = random.nextInt(10, 100);

                        account.withdraw(out);
                    }
                }
            });

            deposits[i].start();
            withdraw.start();
        }
        // TODO: Создайте и запустите потоки, выполняющие случайные операции со счетом

        try {
            deposit.join();
            withdraw.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Финальный баланс: " + account.getBalance());
    }
}
