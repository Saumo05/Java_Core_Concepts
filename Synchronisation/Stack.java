//-------Without Synchronised--------

// public class Stack{
//     private int[] array;
//     private int stackTop;
//     public Stack(int capacity)
//     {
//         array=new int[capacity];
//         stackTop=-1;

//     }

//     public boolean isEmtpty(){
//         return stackTop<0;
//     }

//     public boolean isFull(){
//         return stackTop >= array.length-1;
//     }

//     public boolean push(int element)
//     {
//         if(isFull()) return false;
//         ++stackTop;

//         try{Thread.sleep(1000);}catch(Exception e){}//Thread will sleep for 1000 miliseconds

//         array[stackTop]=element;
//         return true;
//     }
//     public int pop(){
//         if(isEmtpty())return Integer.MIN_VALUE;
//         int obj=array[stackTop];
//         array[stackTop]=Integer.MIN_VALUE;
//         try{Thread.sleep(1000);}catch(Exception e){}

//         stackTop--;
//         return obj;
//     }
// }


//-------With Synchronised--------


public class Stack{
    private int[] array;
    private int stackTop;
    Object lock;
    public Stack(int capacity)
    {
        array=new int[capacity];
        stackTop=-1;
        lock = new Object();
    }

    public boolean isEmtpty(){
        return stackTop<0;
    }

    public boolean isFull(){
        return stackTop >= array.length-1;
    }

    public boolean push(int element)
    {
        //Whichever thread will hold this lock object can access this synchronized block
        synchronized(lock) {if(isFull()) return false;
        ++stackTop;

        try{Thread.sleep(1000);}catch(Exception e){}//Thread will sleep for 1000 miliseconds

        array[stackTop]=element;
        return true;}//This is a critical section and i wont allow multiple threads to access this block of code
    }

    // public synchronized boolean push(int element)
    // {
    //     //Whichever thread will hold this lock object can access this synchronized block
    //     synchronized(lock) {if(isFull()) return false;
    //     ++stackTop;

    //     try{Thread.sleep(1000);}catch(Exception e){}//Thread will sleep for 1000 miliseconds

    //     array[stackTop]=element;
    //     return true;}//This is a critical section and i wont allow multiple threads to access this block of code
    // }
    
    //******************Behind the scenes***************

    //If we synchronize the entire method then behind the scene the entire block is synchornized with this instacne of the current object 
    // synchronized(this)
    // {
    //     ......
    // }

    public int pop(){

        //We will bound the synchronized block with the same lock objects so that when pusher thread is performing push operations popper thread can't 
        //access this block since both the block is bounded by same lock object
        synchronized(lock) {if(isEmtpty())return Integer.MIN_VALUE;
        int obj=array[stackTop];
        array[stackTop]=Integer.MIN_VALUE;
        try{Thread.sleep(1000);}catch(Exception e){}

        stackTop--;
        return obj;}
    }
}