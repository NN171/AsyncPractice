package threads.sync;

import java.util.Random;

public class SynchronizedBankAccountDemo {
    public static void main(String[] args) {
        SynchronizedBankAccount account = new SynchronizedBankAccount();
        Random random = new Random();
        Thread[] deposits = new Thread[2000];
        Thread[] withdraws = new Thread[2000];

//        for (int i = 0; i < deposits.length; i++) {
            Thread deposit = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        int sum = random.nextInt(100, 1000);

                        account.deposit(sum);
                    }
                }
            });

            Thread withdraw = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        int out = random.nextInt(10, 100);

                        account.withdraw(out);
                    }
                }
            });

            deposit.start();
            withdraw.start();
            // TODO: Создайте и запустите потоки, выполняющие случайные операции со счетом

            try {
                deposit.join();
                withdraw.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }

        System.out.println("Финальный баланс: " + account.getBalance());
    }
}
