package multithreading;

public class SynchronizedCounter extends Thread{
    private int counter=0;

    public synchronized void increment(){
        counter++;
    }

    public synchronized int getCounter(){
        return counter;
    }

    public void run(){
        for (int i = 0; i < 10000; i++) {
            increment();
        }
    }
}

