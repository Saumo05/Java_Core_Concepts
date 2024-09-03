public class Thread1 extends Thread{

    public Thread1(String threadName)
    {
        super(threadName);//Setting the name of the Thread by calling the parent class constructor
    }

    @Override
    public void run()//This is the user thread
    {
        for(int i=0;i<5;i++)
        {
            System.out.println("Inside" +Thread.currentThread().getName()+ i);//Getting the current thread name
        }
    }
}