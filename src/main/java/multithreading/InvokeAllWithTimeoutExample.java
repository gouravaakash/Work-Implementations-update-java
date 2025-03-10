package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllWithTimeoutExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int num = i;
            tasks.add(() -> {
                Thread.sleep(num * 1000);
                return num * num;
            });
        }

        try {
            List<Future<Integer>> futures = executor.invokeAll(tasks, 2, TimeUnit.SECONDS);
            for (Future<Integer> future : futures) {
                if (future.isDone()) {
                    System.out.println("Result: " + future.get());
                }
            }
        } catch (CancellationException e) {
            System.out.println("Task did not complete in time.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
