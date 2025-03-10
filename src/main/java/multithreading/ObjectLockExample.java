package multithreading;

public class ObjectLockExample {
    public synchronized void instanceMethod(){
        System.out.println(Thread.currentThread().getName()+" acquired the object");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" released the object");
    }
}

