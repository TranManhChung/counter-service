package server;

import EntityService.AccountService;
import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.CounterServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {
    private static AccountService service= new AccountService();

    @Override
    public void setBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long balance = request.getBalance();

        //set balance from db
        long balanceres = service.setBalance(userId, balance);

        //response balance to client
        responseClient(balanceres, responseObserver);
    }

    @Override
    public void getBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long balance = request.getBalance();

        //get balance from db
        long balanceres = service.getBalance(userId, balance);

        //response balance to client
        responseClient(balanceres, responseObserver);
    }

    @Override
    public void decreaseBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long amount = request.getBalance();

        //decrease balance from db
        long balanceres = service.decreaseBalance(userId, amount);

        //response balance to client
        responseClient(balanceres, responseObserver);
    }

    @Override
    public void increaseBalance(CounterServiceOuterClass.UserReq request, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //get user id from request
        String userId = request.getUserId();
        long amount = request.getBalance();

        //decrease balance from db
        long balanceres = service.increaseBalance(userId, amount);

        //response balance to client
        responseClient(balanceres, responseObserver);
    }

    private void responseClient(long balance, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        //create new response
        CounterServiceOuterClass.BalanceRes res = CounterServiceOuterClass.BalanceRes.newBuilder()
                .setBalance(balance)
                .build();

        //response to client
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
