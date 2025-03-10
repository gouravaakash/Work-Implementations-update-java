package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future result = executorService.submit(()->{
            System.out.println("Task executed using submit(Runnable) by " + Thread.currentThread().getName());
        });
        if(result.isDone()){
            System.out.println("Task is completed");
        }
        executorService.shutdown();
    }
}

