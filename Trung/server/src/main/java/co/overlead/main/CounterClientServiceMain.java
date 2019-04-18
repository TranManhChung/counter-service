package co.overlead.main;

import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.Counterservice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterClientServiceMain {
    private static final Logger logger = Logger.getLogger(CounterClientServiceMain.class.getName());

    private final ManagedChannel channel;
    private final CounterServiceGrpc.CounterServiceBlockingStub blockingStub;

    /** Construct client connecting to server at {@code host:port}. */
    public CounterClientServiceMain(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Some config more
                .usePlaintext()
                .build());
    }

    /** Construct client for accessing server using the existing channel. */
    CounterClientServiceMain(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = CounterServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }



    public void getBalance(String userId) {
        logger.info("Get balance of user: " + userId + " ...");
        Counterservice.UserReq request = Counterservice.UserReq.newBuilder().setUserId(userId).build();
        Counterservice.BalanceRes response;
        try {
            response = blockingStub.getBalance(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("balance: " + response.getBalance());
    }

    public void setBalance(String userId,Long setBalance) {
        logger.info("Creating user: " + userId + " ...");
        Counterservice.UserReq request = Counterservice.UserReq.newBuilder().setUserId(userId).setBalance(setBalance).build();
        Counterservice.BalanceRes response;
        try {
            response = blockingStub.setBalance(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }



    public void decreaseBalance(String userId,Long value) {
        logger.info("decreaseBalance user: " + userId + " ..."+"value:"+value);
        Counterservice.UserReq request = Counterservice.UserReq.newBuilder().setUserId(userId).setBalance(value).build();
        Counterservice.BalanceRes response;
        try {
            response = blockingStub.decreaseBalance(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Result: " + response.getBalance());
    }
    public void increaseBalance(String userId,Long value) {
        logger.info("Creating user: " + userId + " ...");
        Counterservice.UserReq request = Counterservice.UserReq.newBuilder().setUserId(userId).setBalance(value).build();
        Counterservice.BalanceRes response;
        try {
            response = blockingStub.increaseBalance(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Result: " + response.getBalance());
    }
    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        CounterClientServiceMain client = new CounterClientServiceMain("localhost", 9090);
        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "firstuser";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }

           // client.getBalance(user);
            // client.setBalance(user,100L);
            client.increaseBalance(user,500L);
            // client.decreaseBalance(user,200L);
            //client.getBalance(user);
            //client.setBalance(user,-2000L);
            //client.getBalance(user);

        } finally {
            client.shutdown();
        }
    }
}
