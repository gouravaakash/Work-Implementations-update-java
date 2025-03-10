package multithreading;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable thread1=new MyRunnable();
        Thread myThread = new Thread(thread1);
        Thread thread2=new Thread(new MyRunnable());
        myThread.start();
        thread2.start();
        ProducerConsumer pc = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

        MyThread thread1 = new MyThread();
        MyThread thread2= new MyThread();
        MyThread thread3= new MyThread();

        thread1.start();
        thread2.start();
        thread3.start();

        Thread run1=new Thread(new RunnableExample());
        Thread run2=new Thread(new RunnableExample());
        run1.start();
        run2.start();
        Runnable task = LambdaThreadExample.getTask();
        Thread thread1= new Thread(task);
        Thread thread2= new Thread(task);
        thread1.start();
        thread2.start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(task);
        executorService.shutdown();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        ScheduledFuture<String> result = executor.schedule(() -> {
            System.out.println("Thread is executing the job");
            return "completed";
        }, 10, TimeUnit.SECONDS);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

        JoinExample newThread = new JoinExample();
        newThread.start();
        try {
            newThread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread completed");

        Thread Priority
        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        System.out.println(thread3.getPriority());

        thread1.setPriority(10);
        thread2.setPriority(Thread.MIN_PRIORITY);
        System.out.println(thread1.getPriority());
        System.out.println(thread2.getPriority());
        System.out.println(thread3.getPriority());

//      Priority of the Main Thread
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());

        DeamonThreadExample newThread1 = new DeamonThreadExample("Daemon1 Thread");
        DeamonThreadExample newThread2 = new DeamonThreadExample("Daemon2 Thread");

        newThread1.setDaemon(true);

        newThread1.start();
        newThread2.start();
        newThread2.setDaemon(true);

        RaceConditionExample example1 = new RaceConditionExample();
        RaceConditionExample example2 = new RaceConditionExample();
        example1.start();
        example2.start();
        example1.join();
        example2.join();
        System.out.println(RaceConditionExample.counter);

        ObjectLockExample obj1 = new ObjectLockExample();
        ObjectLockExample obj2 = new ObjectLockExample();

        Thread t1=new Thread(()->obj1.instanceMethod(),"Thread-1");
        Thread t2=new Thread(()->obj1.instanceMethod(),"Thread-2");
        Thread t3=new Thread(()->obj2.instanceMethod(),"Thread-3");

        t1.start();
        t2.start();
        t3.start();

        ClassLockExample obj1 = new ClassLockExample();
        ClassLockExample obj2 = new ClassLockExample();

        Thread t1 = new Thread(() -> obj1.staticMethod(), "Thread 1");
        Thread t2 = new Thread(() -> obj2.staticMethod(), "Thread 2");

        t1.start();
        t2.start();

        SynchronizedCounter counterObject=new SynchronizedCounter();
        Thread thread1= new Thread(counterObject);
        Thread thread2= new Thread(counterObject);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Final Counter Value: " + counterObject.getCounter());

        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(500);

        Runnable task = () -> account1.transfer(account2, 200);

        Thread t1=new Thread(task);
        Thread t2=new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Account 1 Balance: " + account1.getBalance());
        System.out.println("Account 2 Balance: " + account2.getBalance());

        List<Integer> taskQueue = new ArrayList<Integer>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
        Thread threadA=new VolatileDemo.ThreadA();
        Thread threadB=new VolatileDemo.ThreadB();

        threadA.start();
        threadB.start();


        threadA.join();
        threadB.join();


    }
}
