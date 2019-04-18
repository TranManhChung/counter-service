package co.overlead.main;

import com.example.grpc.Counterservice;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueReq {

    private static ExecutorService executor = Executors.newFixedThreadPool(1000);//creating a pool of 5 threads
    public QueueReq() {

    }
    private Runnable worker;
    HashMap<String,Queue<RequestType>> listReq=new HashMap<>();;

    public boolean isExistKey(String userId){
        return listReq.containsKey(userId);
    }
    public void addKey(String userId,Long value,String type,StreamObserver<Counterservice.BalanceRes> res) {
        if (isExistKey(userId)){
            listReq.get(userId).add(new RequestType(type, value,res));
        } else {
            listReq.put(userId,new LinkedList<>());
            listReq.get(userId).add(new RequestType(type, value,res));
             worker= new SendThread(userId,listReq.get(userId));
            executor.execute(worker);
        }
    }

    class RequestType{
        private String type;

        private StreamObserver<Counterservice.BalanceRes> res;

        private Long value;
        public String getType() {
            return type;
        }

        public Long getValue() {
            return value;
        }

        public StreamObserver<Counterservice.BalanceRes> getRes() {
            return res;
        }

        public RequestType(String type, Long value,StreamObserver<Counterservice.BalanceRes> res) {
            this.type = type;
            this.value = value;
            this.res=res;
        }

    }


}
