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

public class CounterSeviceServer {
    private static final Logger logger = LogManager.getLogger(CounterSeviceServer.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */

        logger.trace("tracing... ...");
        logger.debug("debuging ... ...");

        logger.info("info ... ... ");
        logger.warn("warning .... ... ");
        logger.error("error ... ... ");
        logger.fatal("fatal ... ...");

        int port = 9090;
        server = ServerBuilder.forPort(port)
                .addService(new CounterServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        /* Add hook when stop application*/
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                // IRedis.USER_SYNC_COMMAND.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                CounterSeviceServer.this.stop();
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
        final CounterSeviceServer server = new CounterSeviceServer();
        server.start();
        server.blockUntilShutdown(); //prevent application shutdown
    }

    static class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {


        @Override
        public void getBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply;
            if (IRedis.USER_SYNC_COMMAND.get(req.getUserId())==null){//NOT EXIST USERID
                reply= Counterservice.BalanceRes.newBuilder().setBalance(0).build();
            } else{
                Long value=Long.parseLong(IRedis.USER_SYNC_COMMAND.get(req.getUserId()).toString());
                reply= Counterservice.BalanceRes.newBuilder().setBalance(value).build();

            }
            responseObserver.onNext(reply);
            responseObserver.onCompleted();

        }


        @Override
        public void increaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply;
            if (IRedis.USER_SYNC_COMMAND.get(req.getUserId())==null){
                Long value=req.getBalance();
                IRedis.USER_SYNC_COMMAND.set(req.getUserId(),value.toString());
                reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();
            } else{
                Long value= req.getBalance();
                IRedis.USER_SYNC_COMMAND.incrby(req.getUserId(),value);
                reply= Counterservice.BalanceRes.newBuilder().setBalance(value).build();
            }
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void decreaseBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply;
            if (IRedis.USER_SYNC_COMMAND.get(req.getUserId())==null){
                Long value=-req.getBalance();
                IRedis.USER_SYNC_COMMAND.set(req.getUserId(),value.toString());
                reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();

            } else{
                Long value=req.getBalance();
                reply= Counterservice.BalanceRes.newBuilder().setBalance(value).build();
                IRedis.USER_SYNC_COMMAND.decrby(req.getUserId(),value);

            }
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void setBalance(Counterservice.UserReq req, StreamObserver<Counterservice.BalanceRes> responseObserver){
            Counterservice.BalanceRes reply;
            if (IRedis.USER_SYNC_COMMAND.get(req.getUserId())==null){
                Long val=req.getBalance();
                IRedis.USER_SYNC_COMMAND.set(req.getUserId(),val.toString());

                reply= Counterservice.BalanceRes.newBuilder().setBalance(req.getBalance()).build();


            } else{
                Long newVal=req.getBalance();
                reply= Counterservice.BalanceRes.newBuilder().setBalance(newVal).build();
                IRedis.USER_SYNC_COMMAND.set(req.getUserId(),newVal.toString());

            }

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
