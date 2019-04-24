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

public class CountSync {
    //private static final Logger logger = LogManager.getLogger(Count.class.getName());
    private static HashMap<String,Long> cacheBalance=new HashMap<>();
    public static HashMap getCacheBalance(){
        return cacheBalance;
    }
    private static QueueReq queueReq=new QueueReq();
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
        server.start();
        server.blockUntilShutdown(); //prevent application shutdown
    }

    static class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {
        public void makeSetCache(String userId,Long newValue){
            Object obj=getCacheBalance().get(userId);
            if (obj==null){
                getCacheBalance().put(userId,newValue);
            } else{
                getCacheBalance().replace(userId,newValue);
            }
            IRedis.USER_SYNC_COMMAND.set(userId,newValue.toString());//update redis
        }
        public void makeIncrCache(String userId,Long newValue){
            Object obj=getCacheBalance().get(userId);
            if (obj==null){
                getCacheBalance().put(userId,newValue);
            } else{
                getCacheBalance().replace(userId,newValue+Long.parseLong(obj.toString()));
            }
            IRedis.USER_SYNC_COMMAND.incrby(userId,newValue);//update redis

        }
        public void makeDecrCache(String userId,Long newValue){
            Object obj=getCacheBalance().get(userId);
            if (obj==null){
                getCacheBalance().put(userId,newValue);
            } else{
                getCacheBalance().replace(userId,Long.parseLong(obj.toString())-newValue);
            }
            IRedis.USER_SYNC_COMMAND.decrby(userId,newValue);//update redis
        }

        @Override
        public void getBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply;
            Object obj=getCacheBalance().get(req.getUserId());
            if(obj!=null){
                reply= Counterservice.BalanceRes.newBuilder().setBalance(Long.parseLong(obj.toString())).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }else{
                reply= Counterservice.BalanceRes.newBuilder().setBalance(0).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void increaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            makeIncrCache(req.getUserId(),req.getBalance());
        }

        @Override
        public void decreaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            makeDecrCache(req.getUserId(),req.getBalance());

        }

        @Override
        public void setBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            makeSetCache(req.getUserId(),req.getBalance());

        }
    }
}