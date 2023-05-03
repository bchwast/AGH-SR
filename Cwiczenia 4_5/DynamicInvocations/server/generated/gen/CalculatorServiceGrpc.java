package gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: calculator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CalculatorServiceGrpc {

  private CalculatorServiceGrpc() {}

  public static final String SERVICE_NAME = "calculator.CalculatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gen.ArithmeticOpArguments,
      gen.ArithmeticOpResult> getOperationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Operation",
      requestType = gen.ArithmeticOpArguments.class,
      responseType = gen.ArithmeticOpResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.ArithmeticOpArguments,
      gen.ArithmeticOpResult> getOperationMethod() {
    io.grpc.MethodDescriptor<gen.ArithmeticOpArguments, gen.ArithmeticOpResult> getOperationMethod;
    if ((getOperationMethod = CalculatorServiceGrpc.getOperationMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getOperationMethod = CalculatorServiceGrpc.getOperationMethod) == null) {
          CalculatorServiceGrpc.getOperationMethod = getOperationMethod =
              io.grpc.MethodDescriptor.<gen.ArithmeticOpArguments, gen.ArithmeticOpResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Operation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.ArithmeticOpArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.ArithmeticOpResult.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("Operation"))
              .build();
        }
      }
    }
    return getOperationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.PrimeTesterArguments,
      gen.PrimeTesterResult> getPrimeTesterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrimeTester",
      requestType = gen.PrimeTesterArguments.class,
      responseType = gen.PrimeTesterResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.PrimeTesterArguments,
      gen.PrimeTesterResult> getPrimeTesterMethod() {
    io.grpc.MethodDescriptor<gen.PrimeTesterArguments, gen.PrimeTesterResult> getPrimeTesterMethod;
    if ((getPrimeTesterMethod = CalculatorServiceGrpc.getPrimeTesterMethod) == null) {
      synchronized (CalculatorServiceGrpc.class) {
        if ((getPrimeTesterMethod = CalculatorServiceGrpc.getPrimeTesterMethod) == null) {
          CalculatorServiceGrpc.getPrimeTesterMethod = getPrimeTesterMethod =
              io.grpc.MethodDescriptor.<gen.PrimeTesterArguments, gen.PrimeTesterResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrimeTester"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimeTesterArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimeTesterResult.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorServiceMethodDescriptorSupplier("PrimeTester"))
              .build();
        }
      }
    }
    return getPrimeTesterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceStub>() {
        @java.lang.Override
        public CalculatorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorServiceStub(channel, callOptions);
        }
      };
    return CalculatorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceBlockingStub>() {
        @java.lang.Override
        public CalculatorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorServiceBlockingStub(channel, callOptions);
        }
      };
    return CalculatorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorServiceFutureStub>() {
        @java.lang.Override
        public CalculatorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorServiceFutureStub(channel, callOptions);
        }
      };
    return CalculatorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void operation(gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<gen.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOperationMethod(), responseObserver);
    }

    /**
     */
    default void primeTester(gen.PrimeTesterArguments request,
        io.grpc.stub.StreamObserver<gen.PrimeTesterResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrimeTesterMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CalculatorService.
   */
  public static abstract class CalculatorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CalculatorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CalculatorService.
   */
  public static final class CalculatorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CalculatorServiceStub> {
    private CalculatorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void operation(gen.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<gen.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOperationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void primeTester(gen.PrimeTesterArguments request,
        io.grpc.stub.StreamObserver<gen.PrimeTesterResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrimeTesterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CalculatorService.
   */
  public static final class CalculatorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CalculatorServiceBlockingStub> {
    private CalculatorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public gen.ArithmeticOpResult operation(gen.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOperationMethod(), getCallOptions(), request);
    }

    /**
     */
    public gen.PrimeTesterResult primeTester(gen.PrimeTesterArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrimeTesterMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CalculatorService.
   */
  public static final class CalculatorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CalculatorServiceFutureStub> {
    private CalculatorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.ArithmeticOpResult> operation(
        gen.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOperationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.PrimeTesterResult> primeTester(
        gen.PrimeTesterArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrimeTesterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OPERATION = 0;
  private static final int METHODID_PRIME_TESTER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OPERATION:
          serviceImpl.operation((gen.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<gen.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_PRIME_TESTER:
          serviceImpl.primeTester((gen.PrimeTesterArguments) request,
              (io.grpc.stub.StreamObserver<gen.PrimeTesterResult>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getOperationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.ArithmeticOpArguments,
              gen.ArithmeticOpResult>(
                service, METHODID_OPERATION)))
        .addMethod(
          getPrimeTesterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.PrimeTesterArguments,
              gen.PrimeTesterResult>(
                service, METHODID_PRIME_TESTER)))
        .build();
  }

  private static abstract class CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.CalculatorServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CalculatorService");
    }
  }

  private static final class CalculatorServiceFileDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier {
    CalculatorServiceFileDescriptorSupplier() {}
  }

  private static final class CalculatorServiceMethodDescriptorSupplier
      extends CalculatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalculatorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CalculatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorServiceFileDescriptorSupplier())
              .addMethod(getOperationMethod())
              .addMethod(getPrimeTesterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
