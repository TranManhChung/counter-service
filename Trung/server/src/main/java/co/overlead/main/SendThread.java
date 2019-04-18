package co.overlead.main;

import co.overlead.database.IRedis;
import com.example.grpc.Counterservice;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObservers;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static co.overlead.main.CounterSeviceServer.getCacheBalance;
class SendThread implements Runnable {
    Queue<QueueReq.RequestType> queue;
    private String name;
    private Runnable worker;
    public SendThread(String name,Queue<QueueReq.RequestType> queue) {
        this.queue=queue;
        this.name=name;
    }
    public void makeCache(QueueReq.RequestType req,Long newValue){
        req.getRes().onNext(Counterservice.BalanceRes.newBuilder().setBalance(newValue).build());
        req.getRes().onCompleted();
        if (CounterSeviceServer.getCacheBalance().containsKey(name)){
            CounterSeviceServer.getCacheBalance().replace(name,newValue);
        }else CounterSeviceServer.getCacheBalance().put(name,newValue);

        IRedis.USER_SYNC_COMMAND.set(name,newValue.toString());//update redis
    }

    @Override
    public void run() {
        while(true){
            if( queue.isEmpty()){
                try {
                    Thread.sleep(100,100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            QueueReq.RequestType req=queue.peek();
            System.out.println(req.getType());
            switch (req.getType()){
                case "GET":
                    if (getCacheBalance().containsKey(name)){//exist in cache
                        req.getRes().onNext(Counterservice.BalanceRes.newBuilder().setBalance(Long.parseLong(getCacheBalance().get(name).toString())).build());
                        req.getRes().onCompleted();

                    } else {//not exist in cache
                        Object value=IRedis.USER_SYNC_COMMAND.get(name); //get from db
                        if (value == null) {//NOT EXIST USERID
                            makeCache(req,req.getValue());
                        } else {
                            makeCache(req,0L);
                        }
                    }
                    break;
                case "SET":

                    makeCache(req,req.getValue());
                    break;
                case "INCR":
                    System.out.println(getCacheBalance().containsKey(name));
                    if (getCacheBalance().containsKey(name)){//in cache
                        Long value=Long.parseLong(getCacheBalance().get(name).toString())+req.getValue();//DANGER
                        makeCache(req,value);

                    } else {
                        Object value=IRedis.USER_SYNC_COMMAND.get(name);
                        if ( value== null) {
                            makeCache(req,req.getValue());
                        } else {
                            Long newValue=Long.parseLong(value.toString());
                            makeCache(req,newValue);
                        }
                    }
                    break;
                case "DECR":
                    if (getCacheBalance().containsKey(name)){
                        Long value=Long.parseLong(getCacheBalance().get(name).toString())- req.getValue();
                        makeCache(req,req.getValue());

                    } else{
                        Object value=IRedis.USER_SYNC_COMMAND.get(name);
                        if (value==null){
                            makeCache(req,-req.getValue());

                        } else{
                            Long newVal= Long.parseLong(value.toString())-req.getValue();
                            makeCache(req,newVal);
                        }

                    }
                    break;
            }
        }


    }
}