package threads.sync;

class AppleBasket {
    private int apples = 0;
    private final int maxApples = 20;

    // Синхронизированный метод для добавления яблок в корзину
    public synchronized boolean pickApple(String picker) {
        if (apples < maxApples) {
            System.out.println(picker + " подобрал яблоко");
            pick();
            return true;
        }
        return false;
    }

    public int getApples() {
        return apples;
    }

    public void pick() {
        apples++;
    }
}
