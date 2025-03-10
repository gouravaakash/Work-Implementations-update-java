package multithreading;

public class ClassLockExample {
    public synchronized static void staticMethod(){
        System.out.println(Thread.currentThread().getName() + " acquired Class Lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " released Class Lock");
    }
}
