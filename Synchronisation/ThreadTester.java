public class ThreadTester {
    public static void main(String[] args) {
        System.out.println("Main is starting");

        Stack stack=new Stack(5);

        new Thread(()->{
            int counter = 0;
            while(++counter<10)
            {
                System.out.println("Pushed: "+stack.push(100));
            }
        },"Pusher").start();

        new Thread(()->{
            int counter=0;
            while(++counter<10)
            System.out.println("Popped: "+stack.pop());
        },"Popper").start();


        //In this case these two threads cannot run parallely because if we allow them to run parrallaly
        // then the push function might wait while pop function pops the element from the array and might decrease the 
        // stackTop to -2 which can cause the push element to push 
        //into array[-1]which will cause array out of bound index Exception


        // -------To Solve this we cant allow multiple threads to change the state of the object variable----------
        //---------------My function can be accessed by only one thread at a time----------         
        //---------To ensure only one thread can access this particular resource we will use the synchronise keyword(can also use locks)----------


        System.out.println("Main is exiting");
    }
}
