package threads.first;

public class BasicThreadDemo {
    public static void main(String[] args) {
        // Создаем и запускаем первый поток, традиционно, через анонимный объект
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Поток 1: " + i);
                    try {
                        // Приостанавливаем поток на 1 секунду
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Создаем и запускаем второй поток, но уже с использованием лямбдавыражения
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Поток 2: " + i);
                try {
                    // Приостанавливаем поток на 1.5 секунды
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Запускаем потоки, они начнут работу параллельно основному потоку
        // Учтите, что порядок выполнения потоков не гарантирован!
        // Запустите программу несколько раз (5-6) и убедитесь в этом...
        thread1.start();
        thread2.start();
        // Ожидаем завершения потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Все потоки завершили работу!");
    }
}
