package threads.sync;

public class SimpleCounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 100000;

        SimpleCounter counter = new SimpleCounter();
        Thread[] threads = new Thread[NUM_THREADS];

       for (int i = 0; i < NUM_THREADS ; i++) {
           final int threadId = i+1;

           threads[i] = new Thread(new Runnable() {
               @Override
               public void run() {
                   for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                       counter.increment();
                       System.out.println("Thread " + threadId + ": " + counter.getCount());
                   }

                   for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                       counter.decrement();
                       System.out.println("Thread " + threadId + ": " + counter.getCount());
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

        System.out.println("Фактическое значение: " + counter.getCount());
    }

}
