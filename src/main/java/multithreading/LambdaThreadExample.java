package multithreading;

public class LambdaThreadExample extends Thread {
    public static Runnable getTask() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" is executing " +i);
            }
        };
    }
}
