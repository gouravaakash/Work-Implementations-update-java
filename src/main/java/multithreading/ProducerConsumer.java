package multithreading;
import java.util.ArrayList;

public class ProducerConsumer extends Thread{
    private final ArrayList<Integer> buffer = new ArrayList<>();
    private final int MAX_CAPACITY=5;
    //producer thread
    public void produce() throws InterruptedException{
        int value=0;
        while(true)
        {
            synchronized (this){
                while(buffer.size()==MAX_CAPACITY){
                    wait();
                }

                System.out.println("Producer produced This Value: "+value);
                buffer.add(value++);
                notify();
                Thread.sleep(500);
            }
        }
    }
    //consumer thread
    public void consume() throws InterruptedException{
        while (true)
        {
            synchronized (this){
                while (buffer.isEmpty()){
                    wait();
                }
                int value=buffer.getLast();
                System.out.println();
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
