package multithreading;

public class RaceConditionExample extends Thread{
    public static int counter=0;
    public void run(){
        for(int i=0;i<10000;i++) {
            counter++;
        }
    }
}

