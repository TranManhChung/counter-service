package co.overlead.main;

import co.overlead.database.IRedis;
import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.Counterservice;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class CountSync {
    private Server server;

    private static final MyCache countCache = new MyCache("countsync", 100);

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

        @Override
        public void getBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver) {
            String userId = req.getUserId();
            int index = countCache.getIndex(userId);

            //cache
            countCache.getExecutorList().get(index).execute(()->{
                Object obj = countCache.getCache().get(userId);
                if (obj != null) {
                    responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(Long.parseLong(obj.toString())).build());
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(0).build());
                    responseObserver.onCompleted();
                }
            });
        }

        @Override
        public void increaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver) {
            String userId = req.getUserId();
            Long newValue = req.getBalance();
            int index = countCache.getIndex(userId);

            //cache
            countCache.getExecutorList().get(index).execute(()->{
                Object obj = countCache.getCache().get(userId);
                if (obj == null) {
                    countCache.getCache().put(userId, newValue);
                } else {
                    countCache.getCache().replace(userId, Long.parseLong(obj.toString()) + newValue);
                }
            });

            //response
            responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(newValue).build());
            responseObserver.onCompleted();

            //add task to queue DB
            countCache.getExecutorDB().execute(() -> {
                IRedis.incrSyncUserBalance(userId, newValue);
            });
        }

        @Override
        public void decreaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver) {
            String userId = req.getUserId();
            Long newValue = req.getBalance();
            int index = countCache.getIndex(userId);

            //cache
            countCache.getExecutorList().get(index).execute(()->{
                Object obj = countCache.getCache().get(userId);
                if (obj == null) {
                    countCache.getCache().put(userId, newValue);
                } else {
                    countCache.getCache().replace(userId, Long.parseLong(obj.toString()) - newValue);
                }
            });

            //response
            responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(newValue).build());
            responseObserver.onCompleted();

            //add task to queue
            countCache.getExecutorDB().execute(() -> {
                IRedis.decrSyncUserBalance(userId, newValue);
            });
        }

        @Override
        public void setBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver) {
            String userId = req.getUserId();
            Long newValue = req.getBalance();
            int index = countCache.getIndex(userId);

            //cache
            countCache.getExecutorList().get(index).execute(()->{
                countCache.getCache().put(userId, newValue);
            });

            //response
            responseObserver.onNext(Counterservice.BalanceRes.newBuilder().setBalance(newValue).build());
            responseObserver.onCompleted();

            //add task to queue
            countCache.getExecutorDB().execute(() -> {
                IRedis.setSyncUserBalance(userId, newValue);
            });
        }
    }
}