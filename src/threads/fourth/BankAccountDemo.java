package threads.fourth;


import java.util.Random;

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Random random = new Random();

        Thread deposit = new Thread(new Runnable() {
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

        deposit.start();
        withdraw.start();
        // TODO: Создайте и запустите потоки, выполняющие случайные операции со счетом

        try {
            deposit.join();
            withdraw.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO: Дождитесь завершения всех операций

        System.out.println("Финальный баланс: " + account.getBalance());
    }
}
