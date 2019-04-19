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
        if (CounterSeviceServer.getCacheBalance().containsKey(req.getKey())){
            CounterSeviceServer.getCacheBalance().replace(req.getKey(),newValue);
        }else CounterSeviceServer.getCacheBalance().put(req.getKey(),newValue);

        IRedis.USER_SYNC_COMMAND.set(req.getKey(),newValue.toString());//update redis
    }

    @Override
    public void run() {
        while(true){
            if( queue.isEmpty()){
                //System.out.println(name+"waiting");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            //System.out.println(name+"processing");
            QueueReq.RequestType req=queue.remove();
            if (req==null) continue;
            switch (req.getType()){
                case "GET":

                    if (getCacheBalance().containsKey(req.getKey())){//exist in cache
                        req.getRes().onNext(Counterservice.BalanceRes.newBuilder().setBalance(Long.parseLong(getCacheBalance().get(req.getKey()).toString())).build());
                        req.getRes().onCompleted();

                    } else {//not exist in cache
                        Object value=IRedis.USER_SYNC_COMMAND.get(req.getKey()); //get from db
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
                    if (getCacheBalance().containsKey(req.getKey())){//in cache
                        Long value=Long.parseLong(getCacheBalance().get(req.getKey()).toString())+req.getValue();//DANGER
                        makeCache(req,value);

                    } else {
                        Object value=IRedis.USER_SYNC_COMMAND.get(req.getKey());
                        if ( value== null) {
                            makeCache(req,req.getValue());
                        } else {
                            Long newValue=Long.parseLong(value.toString());
                            makeCache(req,newValue);
                        }
                    }
                    break;
                case "DECR":

                    if (getCacheBalance().containsKey(req.getKey())){
                        Long value=Long.parseLong(getCacheBalance().get(req.getKey()).toString())- req.getValue();
                        makeCache(req,req.getValue());

                    } else{
                        Object value=IRedis.USER_SYNC_COMMAND.get(req.getKey());
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