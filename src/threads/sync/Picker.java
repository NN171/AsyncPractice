package threads.sync;

class Picker implements Runnable {
    private final AppleBasket basket;
    private final String name;

    public Picker(AppleBasket basket, String name) {
        this.basket = basket;
        this.name = name;
    }

    @Override
    public void run() {
        while (basket.pickApple(name)) {
            try {
                Thread.sleep(100);  // Имитация времени на сбор яблока
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

