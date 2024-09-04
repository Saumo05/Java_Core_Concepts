package Singleton;

public class TvSet{
    private static volatile TvSet tvSetInstance = null;

    private TvSet(){
        System.out.println("TV Set instantiated");
    }
    public static TvSet getTvSetInstance() {
        if(tvSetInstance == null){
            synchronized(TvSet.class)
            {
                if(tvSetInstance == null){//Double checking
                    tvSetInstance = new TvSet();
                }
            }
        }
        return tvSetInstance;
    }
}

// A singleton class is a class which can initiate only one object so here if we dont use volatile keyword
//Then a thread might have created one object of the class and stored in the cache which took some time to get updated in the main memory
//while another got access to the critical section and saw that it is still null so that Thread also initiated another object of that class
