package threads.second;

public class CounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 100000;

        Counter counter = new Counter();
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadNumber = i+1;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                        counter.increment();
                        System.out.println("Thread " + threadNumber + ": " + counter.getCount());
                    }
                }
            });
            threads[i].start();
        }
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ожидаемое значение: " + (NUM_THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Фактическое значение: " + counter.getCount());
    }
}
