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

    private static ExecutorService executor = Executors.newFixedThreadPool(2);//creating a pool of 5 threads
    private static ExecutorService executor1 = Executors.newSingleThreadExecutor();//creating a pool of 5 threads
    private static ExecutorService executor2 = Executors.newSingleThreadExecutor();//creating a pool of 5 threads

    public QueueReq() {
        listReq.put("list1",new LinkedList<>());
        listReq.put("list2",new LinkedList<>());

        Runnable worker1= new SendThread("list1",listReq.get("list1"));
        Runnable worker2= new SendThread("list2",listReq.get("list2"));

        executor.execute(worker1);
        executor.execute(worker2);

    }
    HashMap<String,Queue<RequestType>> listReq=new HashMap<>();
    HashMap<String,Queue<RequestType>> listReq2=new HashMap<>();

    public boolean isExistKey(String userId){
        return listReq.containsKey(userId);
    }
    public void addKey(String userId,Long value,String type,StreamObserver<Counterservice.BalanceRes> res) {
        if (userId.charAt(0)>'h'){
            listReq.get("list1").add(new RequestType(type,userId, value,res));
        } else {
            listReq.get("list2").add(new RequestType(type,userId, value,res));
        }
    }

    class RequestType{
        private String type;

        private StreamObserver<Counterservice.BalanceRes> res;

        public String getKey() {
            return key;
        }

        private String key;

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

        public RequestType(String type, String key,Long value,StreamObserver<Counterservice.BalanceRes> res) {
            this.type = type;
            this.key=key;
            this.value = value;
            this.res=res;
        }

    }


}
