package Producer_Consumer;

import java.util.*;

public class BlockingQueue {
    private Queue<Integer> q;

    private int capacity;


    public BlockingQueue(int cap)
    {
        q = new LinkedList<>();
        capacity = cap;
    }

    public boolean add(int item)
    {
        synchronized(q){
            while(q.size()==capacity)//Use while instead of if because if there is a case of multiple threads waiting then it will check again if there is capacity for adding
            //This thread will wait if any remover function removes item from the queues to decrease the full capacity
            {
                try{
                    q.wait();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            q.add(item);
            q.notifyAll();//Notify all the threads that are in the wait state that items have been added in the queue
            return true;
        }
    }

    public int remove(){
        synchronized(q){
            while(q.size()==0)
            //This thread will wait if any adder thread adds item to the Queue to perform the action
            {
                try{
                    q.wait();//It has to wait because it cannot perform its action
                }catch(Exception e )
                {
                    e.printStackTrace();
                }
                
            }

            int element=q.poll();
            q.notifyAll();//notify the adder threads about removing element
            return element;
        }
    }
}

//As we are using the same lock therefore at a time only one lock gets accessed to the block which can add or remove items from the queue
