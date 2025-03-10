package multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableWithResultExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future=executorService.submit(()->{
            System.out.println("Task executed using submit(Runnable, T) by " + Thread.currentThread().getName());
        },"Task Completed!");

        String result=future.get();
        System.out.println("Result from future: "+result);
        executorService.shutdown();
    }
}
