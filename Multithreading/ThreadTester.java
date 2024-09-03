public class ThreadTester{
    
    public static void main(String[] args)
    {
        System.out.println("Main is starting");
        Thread thread1= new Thread1("ThreadPoltu");//Here our own run method is getting executed because we are extending the Thread class
        //thread1.setDaemon(true);//It wil stop execution after the main thread is executed because it is not a user thread
        thread1.start();//Program will continue to run till all the user threads continue to run
        
        Thread thread2 = new Thread(()->
        {//Lambda function :- Passing the implemntation of run method as a parameter to avoid writing boilerplate code
            for(int i=0;i<5;i++)
            {
                System.out.println(Thread.currentThread());
            }
        }, "thread2");//Passing object of Runnable interface
        thread2.start();

        System.out.println("Main is Executing");

        //Inside the thread class there is a Runnable instance called task so if we simply call the run method 
        //the task is set to null and the run method will not execute but if we pass the object then it will call the run method which we have implemented
    }

//The order of the execution cannot be predicted because it depends on platform
}

