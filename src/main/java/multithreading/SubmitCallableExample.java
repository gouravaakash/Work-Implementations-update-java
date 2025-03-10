package multithreading;

import java.util.concurrent.*;

public class SubmitCallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Callable<Integer> task= ()->{
            int sum=0;
            for(int i=0;i<10;i++){
                sum+=i;
                Thread.sleep(500);
            }
            return sum;
        };

        Future<Integer> future=executorService.submit(task);
        int result=future.get();
        System.out.println("Result from callable: "+result);
        executorService.shutdown();
    }
}
