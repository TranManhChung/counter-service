package examples.gRPC;

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
    comments = "Source: counter.proto")
public final class CounterServiceGrpc {

  private CounterServiceGrpc() {}

  public static final String SERVICE_NAME = "counter.CounterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<examples.gRPC.UserReq,
      examples.gRPC.Result> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = examples.gRPC.UserReq.class,
      responseType = examples.gRPC.Result.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.gRPC.UserReq,
      examples.gRPC.Result> getCreateUserMethod() {
    io.grpc.MethodDescriptor<examples.gRPC.UserReq, examples.gRPC.Result> getCreateUserMethod;
    if ((getCreateUserMethod = CounterServiceGrpc.getCreateUserMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getCreateUserMethod = CounterServiceGrpc.getCreateUserMethod) == null) {
          CounterServiceGrpc.getCreateUserMethod = getCreateUserMethod = 
              io.grpc.MethodDescriptor.<examples.gRPC.UserReq, examples.gRPC.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "counter.CounterService", "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("CreateUser"))
                  .build();
          }
        }
     }
     return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.gRPC.UserReq,
      examples.gRPC.GetBalanceReply> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = examples.gRPC.UserReq.class,
      responseType = examples.gRPC.GetBalanceReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.gRPC.UserReq,
      examples.gRPC.GetBalanceReply> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<examples.gRPC.UserReq, examples.gRPC.GetBalanceReply> getGetBalanceMethod;
    if ((getGetBalanceMethod = CounterServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getGetBalanceMethod = CounterServiceGrpc.getGetBalanceMethod) == null) {
          CounterServiceGrpc.getGetBalanceMethod = getGetBalanceMethod = 
              io.grpc.MethodDescriptor.<examples.gRPC.UserReq, examples.gRPC.GetBalanceReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "counter.CounterService", "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.UserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.GetBalanceReply.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("GetBalance"))
                  .build();
          }
        }
     }
     return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<examples.gRPC.SetBalanceReq,
      examples.gRPC.GetBalanceReply> getSetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBalance",
      requestType = examples.gRPC.SetBalanceReq.class,
      responseType = examples.gRPC.GetBalanceReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<examples.gRPC.SetBalanceReq,
      examples.gRPC.GetBalanceReply> getSetBalanceMethod() {
    io.grpc.MethodDescriptor<examples.gRPC.SetBalanceReq, examples.gRPC.GetBalanceReply> getSetBalanceMethod;
    if ((getSetBalanceMethod = CounterServiceGrpc.getSetBalanceMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getSetBalanceMethod = CounterServiceGrpc.getSetBalanceMethod) == null) {
          CounterServiceGrpc.getSetBalanceMethod = getSetBalanceMethod = 
              io.grpc.MethodDescriptor.<examples.gRPC.SetBalanceReq, examples.gRPC.GetBalanceReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "counter.CounterService", "SetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.SetBalanceReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  examples.gRPC.GetBalanceReply.getDefaultInstance()))
                  .setSchemaDescriptor(new CounterServiceMethodDescriptorSupplier("SetBalance"))
                  .build();
          }
        }
     }
     return getSetBalanceMethod;
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
    public void createUser(examples.gRPC.UserReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    public void getBalance(examples.gRPC.UserReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     */
    public void setBalance(examples.gRPC.SetBalanceReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSetBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                examples.gRPC.UserReq,
                examples.gRPC.Result>(
                  this, METHODID_CREATE_USER)))
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                examples.gRPC.UserReq,
                examples.gRPC.GetBalanceReply>(
                  this, METHODID_GET_BALANCE)))
          .addMethod(
            getSetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                examples.gRPC.SetBalanceReq,
                examples.gRPC.GetBalanceReply>(
                  this, METHODID_SET_BALANCE)))
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
    public void createUser(examples.gRPC.UserReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBalance(examples.gRPC.UserReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setBalance(examples.gRPC.SetBalanceReq request,
        io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request, responseObserver);
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
    public examples.gRPC.Result createUser(examples.gRPC.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.gRPC.GetBalanceReply getBalance(examples.gRPC.UserReq request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public examples.gRPC.GetBalanceReply setBalance(examples.gRPC.SetBalanceReq request) {
      return blockingUnaryCall(
          getChannel(), getSetBalanceMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<examples.gRPC.Result> createUser(
        examples.gRPC.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.gRPC.GetBalanceReply> getBalance(
        examples.gRPC.UserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<examples.gRPC.GetBalanceReply> setBalance(
        examples.gRPC.SetBalanceReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_GET_BALANCE = 1;
  private static final int METHODID_SET_BALANCE = 2;

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
        case METHODID_CREATE_USER:
          serviceImpl.createUser((examples.gRPC.UserReq) request,
              (io.grpc.stub.StreamObserver<examples.gRPC.Result>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((examples.gRPC.UserReq) request,
              (io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply>) responseObserver);
          break;
        case METHODID_SET_BALANCE:
          serviceImpl.setBalance((examples.gRPC.SetBalanceReq) request,
              (io.grpc.stub.StreamObserver<examples.gRPC.GetBalanceReply>) responseObserver);
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
      return examples.gRPC.CounterProto.getDescriptor();
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
              .addMethod(getCreateUserMethod())
              .addMethod(getGetBalanceMethod())
              .addMethod(getSetBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
