package com.wallet.counter.services;


import com.example.grpc.CounterServiceGrpc;
import com.example.grpc.CounterServiceOuterClass;
import com.wallet.counter.models.Balance;
import com.wallet.counter.repositories.BalanceRepository;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class CounterServiceImpl extends CounterServiceGrpc.CounterServiceImplBase {

    @Autowired
    BalanceRepository balanceRepository;

    private final static ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();//chỉ cho 1 tiến trình viết tại một thời điểm
    private static Map<String, Long> temp = new HashMap<>();//lưu dữ liệu trên bộ nhớ

    final private static ExecutorService childThread = Executors.newSingleThreadExecutor();//tạo mới một thread

    @Override
    public void setBalance(CounterServiceOuterClass.UserReq request,
                           StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        generateResponse(request.getBalance(), responseObserver);
        childThread.execute(()->{
            try{
                writeLock.lock();
                balanceRepository.save(new Balance(request.getUserId(), request.getBalance()));
            }finally {
                writeLock.unlock();
            }
        });
    }

    @Override
    public void getBalance(CounterServiceOuterClass.UserReq request,
                           StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        Long responseMess;
        if (temp.get(request.getUserId()) != null) {//nếu đối tượng đã tồn tại trong temp thì get ra
            responseMess = temp.get(request.getUserId());
        } else {//nếu chưa tồn tại tiến hành đọc từ db lên
            Optional<Balance> balance = balanceRepository.findById(request.getUserId());
            if (balance.isPresent()) {//nếu tồn tại đối tượng dứi db
                responseMess = balance.get().getBalanceValue();
                temp.put(request.getUserId(), responseMess);
            } else {
                responseMess = request.getBalance();
            }
        }
        generateResponse(responseMess, responseObserver);
    }

    @Override
    public void increaseBalance(CounterServiceOuterClass.UserReq request,
                                StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        isDecreaseBalance(request, responseObserver, false);
    }

    @Override
    public void decreaseBalance(CounterServiceOuterClass.UserReq request,
                                StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        isDecreaseBalance(request, responseObserver, true);
    }


    public void isDecreaseBalance(CounterServiceOuterClass.UserReq request,
                                  StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver, boolean type) {
        generateResponse(request.getBalance(), responseObserver);
        childThread.execute(() -> {
            try {
                writeLock.lock();
                Long responseMess = request.getBalance();
                String userId = request.getUserId();
                Optional<Balance> balance = balanceRepository.findById(userId);
                if (balance.isPresent()) {
                    if(type == true){
                        responseMess = balance.get().getBalanceValue() - responseMess;
                    }else {
                        responseMess = balance.get().getBalanceValue() + responseMess;
                    }
                }
                temp.put(request.getUserId(), responseMess);
                balanceRepository.save(new Balance(request.getUserId(), responseMess));
            } finally {
                writeLock.unlock();
            }
        });
    }

    public void generateResponse(Long responseMess, StreamObserver<CounterServiceOuterClass.BalanceRes> responseObserver) {
        CounterServiceOuterClass.BalanceRes balanceRes = CounterServiceOuterClass.BalanceRes.newBuilder().setBalance(responseMess)
                .build();
        responseObserver.onNext(balanceRes);
        responseObserver.onCompleted();
    }
}
