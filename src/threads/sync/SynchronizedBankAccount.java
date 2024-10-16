package threads.sync;

public class SynchronizedBankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        this.balance += amount;
//        transaction(amount);
        System.out.println("Добавлено: " + amount + "\nБаланс: " + getBalance() + "\n");
    }

    public synchronized void withdraw(int amount) {
        this.balance -= amount;
//        transaction(-amount);
        System.out.println("Выведено: " + amount + "\nБаланс: " + getBalance() + "\n");
    }

//    public synchronized void transaction(int amount) {
//        this.balance += amount;
//    }

    public int getBalance() {
        return this.balance;
    }
}
