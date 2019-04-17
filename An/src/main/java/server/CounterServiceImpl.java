package server;


import DataAccess.AccountDAO;
import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.CounterServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {
    private static AccountDAO accountDA = new AccountDAO();

    @Override
    public void setBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long balance = request.getBalance();

        //set balance from db
        accountDA.setBalance(userId, balance);

        //response balance to client
        CounterServiceOuterClass.BalanceRes res = CounterServiceOuterClass.BalanceRes.newBuilder()
                .setBalance(accountDA.getBalance(userId, balance))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long balance = request.getBalance();

        //get balance from db
        long balanceres = accountDA.getBalance(userId, balance);

        //response balance to client
        CounterServiceOuterClass.BalanceRes res = CounterServiceOuterClass.BalanceRes.newBuilder()
                .setBalance(balanceres)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void decreaseBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long amount = request.getBalance();

        //decrease balance from db
        accountDA.decreaseBalance(userId, amount);

        //response balance to client
        CounterServiceOuterClass.BalanceRes res = CounterServiceOuterClass.BalanceRes.newBuilder()
                .setBalance(accountDA.getBalance(userId, amount))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void increaseBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long amount = request.getBalance();

        //decrease balance from db
        accountDA.increaseBalance(userId, amount);

        //response balance to client
        CounterServiceOuterClass.BalanceRes res = CounterServiceOuterClass.BalanceRes.newBuilder()
                .setBalance(accountDA.getBalance(userId, amount))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
