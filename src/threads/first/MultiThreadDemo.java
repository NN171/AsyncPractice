package threads.first;

public class MultiThreadDemo {

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            final int threadNumber = i + 1;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println("Thread " + threadNumber + ": " + j);
                    }
                }
            });

            threads[i].start();
        }
        try {
            for (int i = 0; i < 5; i++) {
                threads[i].join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Threads has finished");
    }
}
