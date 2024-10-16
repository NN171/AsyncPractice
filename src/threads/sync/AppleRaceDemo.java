package threads.sync;

class AppleRaceDemo {
    public static void main(String[] args) {
        AppleBasket basket = new AppleBasket();

        Thread picker1 = new Thread(new Picker(basket, "Picker 1"));
        Thread picker2 = new Thread(new Picker(basket, "Picker 2"));

        picker1.start();
        picker2.start();

        try {
            picker1.join();
            picker2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Создайте двух сборщиков (потоки) и подождите завершения

        System.out.println("Гонка завершена! Всего собрано яблок: " + basket.getApples());
    }
}
