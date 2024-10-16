package threads.third;

public class RacerDemo {
    public static void main(String[] args) {
        Thread racer1 = new Thread(new Racer("Racer 1"));
        Thread racer2 = new Thread(new Racer("Racer 2"));
        Thread racer3 = new Thread(new Racer("Racer 3"));
        Thread racer4 = new Thread(new Racer("Racer 4"));
        Thread racer5 = new Thread(new Racer("Racer 5"));

        racer1.start();
        racer2.start();
        racer3.start();
        racer4.start();
        racer5.start();

        try {
            racer1.join();
            racer2.join();
            racer3.join();
            racer4.join();
            racer5.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
