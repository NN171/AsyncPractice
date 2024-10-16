package threads.fourth;

class BankAccount {
    private int balance = 1000;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Добавлено: " + amount + "\nБаланс: " + balance + "\n");
    }

    public void withdraw(int amount) {
        balance -= amount;
        System.out.println("Выведено: " + amount + "\nБаланс: " + balance + "\n");
    }

    public int getBalance() {
        return balance;
    }
}
