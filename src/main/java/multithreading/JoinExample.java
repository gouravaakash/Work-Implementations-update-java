package multithreading;

public class JoinExample extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread Execution " + i);
        }
    }
}
