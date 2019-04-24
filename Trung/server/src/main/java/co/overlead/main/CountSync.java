package co.overlead.main;

import co.overlead.database.IRedis;
import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.Counterservice;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountSync {
    private static final int NUMCACHE=1;
    private static HashMap<Integer, HashMap<String,Long>> cacheBalanceList=new HashMap<>();
    private static HashMap<Integer,ExecutorService> executorList=new HashMap<>();

    public static int getIndex(String id){
        int index=id.hashCode()%NUMCACHE;
        if (index<0) return -index;
        else return index;
    }

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */


        int port = 9090;
        server = ServerBuilder.forPort(port)
                .addService(new CounterServiceImpl())
                .build()
                .start();
        // logger.info("Server started, listening on " + port);

        /* Add hook when stop application*/
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                // IRedis.USER_SYNC_COMMAND.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                CountSync.this.stop();
                System.err.println("*** server shut down");

            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        final CountSync server = new CountSync();
        for (int i=0;i<NUMCACHE;i++){
            cacheBalanceList.put(i,new HashMap<String, Long>());
            executorList.put(i,Executors.newSingleThreadExecutor());
        }
        server.start();

        server.blockUntilShutdown(); //prevent application shutdown
    }

    static class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {
        public void makeSetCache(String userId,Long newValue){

            int index= getIndex(userId);
            Object obj=cacheBalanceList.get(index).get(userId);
            if (obj==null){
                cacheBalanceList.get(index).put(userId,newValue);
            } else{
                cacheBalanceList.get(index).replace(userId,newValue);
            }
            IRedis.USER_SYNC_COMMAND.set(userId,newValue.toString());//update redis


        }
        public void makeIncrCache(String userId,Long newValue){
            int index= getIndex(userId);

            Object obj = cacheBalanceList.get(index).get(userId);
            if (obj==null){
                cacheBalanceList.get(index).put(userId,newValue);
            } else{
                cacheBalanceList.get(index).replace(userId,newValue+Long.parseLong(obj.toString()));
            }
            IRedis.USER_SYNC_COMMAND.incrby(userId,newValue);//update redis
        }
        public void makeDecrCache(String userId,Long newValue){
            int index= getIndex(userId);

            Object obj=cacheBalanceList.get(index).get(userId);
            if (obj==null){
                cacheBalanceList.get(index).put(userId,newValue);
            } else{
                cacheBalanceList.get(index).replace(userId,Long.parseLong(obj.toString())-newValue);
            }
            IRedis.USER_SYNC_COMMAND.decrby(userId,newValue);//update redis
        }

        public void makeGetCache(Long balance,StreamObserver<Counterservice.BalanceRes> responseObserver){

            responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(balance).build());
            responseObserver.onCompleted();


        }
        @Override
        public void getBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            int index= getIndex(req.getUserId());
            Counterservice.BalanceRes reply;
            Object obj=cacheBalanceList.get(index).get(req.getUserId());

            if(obj!=null){
                executorList.get(getIndex(req.getUserId())).execute(()->{
                    makeGetCache(Long.parseLong(obj.toString()),responseObserver);
                });


            }else{
                reply= Counterservice.BalanceRes.newBuilder().setBalance(0).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void increaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){


            executorList.get(getIndex(req.getUserId())).execute(()->{
                makeIncrCache(req.getUserId(),req.getBalance());
            });


            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void decreaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){


            executorList.get(getIndex(req.getUserId())).execute(()->{
                makeDecrCache(req.getUserId(),req.getBalance());
            });

            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void setBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){

            executorList.get(getIndex(req.getUserId())).execute(()->{
                makeSetCache(req.getUserId(),req.getBalance());
            });


            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();

        }
    }
}