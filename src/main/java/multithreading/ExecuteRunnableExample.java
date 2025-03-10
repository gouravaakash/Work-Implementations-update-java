package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()-> {
            System.out.println("Task is getting executed by the thread with the thread name "+Thread.currentThread().getName());
        });
        executorService.shutdown();
    }
}

