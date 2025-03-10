package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> tasks= new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int num = i;
            tasks.add(() -> {
                Thread.sleep(1000);
                return num * num;
            });
        }
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        try{
            for (Future<Integer> future : futures) {
                System.out.println("Result from Callable: " + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
