package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: CounterService.proto")
public final class CounterServiceGrpc {

  private CounterServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.CounterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getSetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBalance",
      requestType = com.example.grpc.CounterServiceOuterClass.UserReq.class,
      responseType = com.example.grpc.CounterServiceOuterClass.BalanceRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getSetBalanceMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes> getSetBalanceMethod;
    if ((getSetBalanceMethod = CounterServiceGrpc.getSetBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getSetBalanceMethod = CounterServiceGrpc.getSetBalanceMethod) == null) {
          CounterServiceGrpc.getSetBalanceMethod = getSetBalanceMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.CounterService", "SetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.BalanceRes.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("SetBalance"))
                  .build();
          }
        }
     }
     return getSetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = com.example.grpc.CounterServiceOuterClass.UserReq.class,
      responseType = com.example.grpc.CounterServiceOuterClass.BalanceRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes> getGetBalanceMethod;
    if ((getGetBalanceMethod = CounterServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getGetBalanceMethod = CounterServiceGrpc.getGetBalanceMethod) == null) {
          CounterServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.CounterService", "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.BalanceRes.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("GetBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getIncreaseBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IncreaseBalance",
      requestType = com.example.grpc.CounterServiceOuterClass.UserReq.class,
      responseType = com.example.grpc.CounterServiceOuterClass.BalanceRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getIncreaseBalanceMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes> getIncreaseBalanceMethod;
    if ((getIncreaseBalanceMethod = CounterServiceGrpc.getIncreaseBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getIncreaseBalanceMethod = CounterServiceGrpc.getIncreaseBalanceMethod) == null) {
          CounterServiceGrpc.getIncreaseBalanceMethod = getIncreaseBalanceMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.CounterService", "IncreaseBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.BalanceRes.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("IncreaseBalance"))
                  .build();
          }
        }
     }
     return getIncreaseBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getDecreaseBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecreaseBalance",
      requestType = com.example.grpc.CounterServiceOuterClass.UserReq.class,
      responseType = com.example.grpc.CounterServiceOuterClass.BalanceRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq,
      com.example.grpc.CounterServiceOuterClass.BalanceRes> getDecreaseBalanceMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes> getDecreaseBalanceMethod;
    if ((getDecreaseBalanceMethod = CounterServiceGrpc.getDecreaseBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getDecreaseBalanceMethod = CounterServiceGrpc.getDecreaseBalanceMethod) == null) {
          CounterServiceGrpc.getDecreaseBalanceMethod = getDecreaseBalanceMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.CounterServiceOuterClass.UserReq, com.example.grpc.CounterServiceOuterClass.BalanceRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.CounterService", "DecreaseBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.CounterServiceOuterClass.BalanceRes.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("DecreaseBalance"))
                  .build();
          }
        }
     }
     return getDecreaseBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CounterServiceStub newStub(io.grpc.Channel channel) {
    return new CounterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CounterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CounterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CounterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CounterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CounterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void setBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnimplementedUnaryCall(getSetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void getBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void increaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnimplementedUnaryCall(getIncreaseBalanceMethod(), responseObserver);
    }

    /**
     */
    public void decreaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnimplementedUnaryCall(getDecreaseBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.CounterServiceOuterClass.UserReq,
                com.example.grpc.CounterServiceOuterClass.BalanceRes>(
                  this, METHODID_SET_BALANCE)))
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.CounterServiceOuterClass.UserReq,
                com.example.grpc.CounterServiceOuterClass.BalanceRes>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getIncreaseBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.CounterServiceOuterClass.UserReq,
                com.example.grpc.CounterServiceOuterClass.BalanceRes>(
                  this, METHODID_INCREASE_BALANCE)))
          .addMethod(
            getDecreaseBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.CounterServiceOuterClass.UserReq,
                com.example.grpc.CounterServiceOuterClass.BalanceRes>(
                  this, METHODID_DECREASE_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class CounterServiceStub extends io.grpc.stub.AbstractStub<CounterServiceStub> {
    private CounterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CounterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CounterServiceStub(channel, callOptions);
    }

    /**
     */
    public void setBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void increaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncreaseBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decreaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request,
        io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecreaseBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CounterServiceBlockingStub extends io.grpc.stub.AbstractStub<CounterServiceBlockingStub> {
    private CounterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CounterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CounterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.CounterServiceOuterClass.BalanceRes setBalance(com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getSetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.CounterServiceOuterClass.BalanceRes getBalance(com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.CounterServiceOuterClass.BalanceRes increaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getIncreaseBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.CounterServiceOuterClass.BalanceRes decreaseBalance(com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getDecreaseBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CounterServiceFutureStub extends io.grpc.stub.AbstractStub<CounterServiceFutureStub> {
    private CounterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CounterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CounterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.CounterServiceOuterClass.BalanceRes> setBalance(
        com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.CounterServiceOuterClass.BalanceRes> getBalance(
        com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.CounterServiceOuterClass.BalanceRes> increaseBalance(
        com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getIncreaseBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.CounterServiceOuterClass.BalanceRes> decreaseBalance(
        com.example.grpc.CounterServiceOuterClass.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDecreaseBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_BALANCE = 0;
  private static final int METHODID_GET_BALANCE = 1;
  private static final int METHODID_INCREASE_BALANCE = 2;
  private static final int METHODID_DECREASE_BALANCE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CounterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CounterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_BALANCE:
          serviceImpl.setBalance((com.example.grpc.CounterServiceOuterClass.UserReq) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((com.example.grpc.CounterServiceOuterClass.UserReq) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes>) responseObserver);
          break;
        case METHODID_INCREASE_BALANCE:
          serviceImpl.increaseBalance((com.example.grpc.CounterServiceOuterClass.UserReq) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes>) responseObserver);
          break;
        case METHODID_DECREASE_BALANCE:
          serviceImpl.decreaseBalance((com.example.grpc.CounterServiceOuterClass.UserReq) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.CounterServiceOuterClass.BalanceRes>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CounterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.CounterServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CounterService");
    }
  }

  private static final class CounterServiceFileDescriptorSupplier
      extends CounterServiceBaseDescriptorSupplier {
    CounterServiceFileDescriptorSupplier() {}
  }

  private static final class CounterServiceMethodDescriptorSupplier
      extends CounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CounterServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CounterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CounterServiceFileDescriptorSupplier())
              .addMethod(getSetBalanceMethod())
              .addMethod(getGetBalanceMethod())
              .addMethod(getIncreaseBalanceMethod())
              .addMethod(getDecreaseBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
