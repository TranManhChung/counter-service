package co.overlead.main;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCache {
    private String name;
    private final int NUM_HASH;
    private  HashMap<String,Long> cache;
    private HashMap<Integer, ExecutorService> executorList;
    private ExecutorService executorDB;

    public MyCache(String name,int num){
        cache =new HashMap<>();
        executorList=new HashMap<>();
        executorDB= Executors.newFixedThreadPool(1);
        NUM_HASH=num;
        for (int i=0;i<num;i++){
            executorList.put(i,Executors.newFixedThreadPool(1));
        }
    }
    public  HashMap<String, Long> getCache() {
        return cache;
    }

    public HashMap<Integer, ExecutorService> getExecutorList() {
        return executorList;
    }

    public ExecutorService getExecutorDB() {
        return executorDB;
    }
    public int getIndex(String id){
        int index=id.hashCode()%NUM_HASH;
        if (index<0) return -index;
        else return index;
    }
}
