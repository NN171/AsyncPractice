package threads.third;

public class Racer implements Runnable{

    private String name;
    private final int RACE_END = 1_000_000;
    private int count = 0;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < RACE_END; i++) {
            count++;
            System.out.println(name + ": " + count);
        }
        long endTime = System.nanoTime();

        System.out.println("Финиш: " + (endTime/1_000_000_000.0));
        System.out.println("Время гонки: " + (endTime - startTime)/1_000_000_000.0);

    }
}
