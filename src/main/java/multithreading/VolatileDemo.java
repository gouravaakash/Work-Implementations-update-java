package multithreading;

public class VolatileDemo{
    public static volatile int increment=0;
    static class ThreadA extends Thread{
        @Override
        public void run(){
            int x=increment;
            while (true){
                if(x!=increment){
                    System.out.println("Increment value changed: "+increment);
                    x=increment;
                }
            }
        }
    }
    static class ThreadB extends Thread{
        @Override
        public void run() {
            while (increment<10){
                System.out.println("Incrementing the variable "+ ++increment);
                try {
                    Thread.sleep(600);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
