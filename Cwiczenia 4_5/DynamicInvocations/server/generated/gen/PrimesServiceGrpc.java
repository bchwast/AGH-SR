package gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: primes.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PrimesServiceGrpc {

  private PrimesServiceGrpc() {}

  public static final String SERVICE_NAME = "calculator.PrimesService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gen.PrimesInput,
      gen.PrimesStreamResult> getStreamPrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamPrimeNumbers",
      requestType = gen.PrimesInput.class,
      responseType = gen.PrimesStreamResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gen.PrimesInput,
      gen.PrimesStreamResult> getStreamPrimeNumbersMethod() {
    io.grpc.MethodDescriptor<gen.PrimesInput, gen.PrimesStreamResult> getStreamPrimeNumbersMethod;
    if ((getStreamPrimeNumbersMethod = PrimesServiceGrpc.getStreamPrimeNumbersMethod) == null) {
      synchronized (PrimesServiceGrpc.class) {
        if ((getStreamPrimeNumbersMethod = PrimesServiceGrpc.getStreamPrimeNumbersMethod) == null) {
          PrimesServiceGrpc.getStreamPrimeNumbersMethod = getStreamPrimeNumbersMethod =
              io.grpc.MethodDescriptor.<gen.PrimesInput, gen.PrimesStreamResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamPrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimesInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimesStreamResult.getDefaultInstance()))
              .setSchemaDescriptor(new PrimesServiceMethodDescriptorSupplier("StreamPrimeNumbers"))
              .build();
        }
      }
    }
    return getStreamPrimeNumbersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.PrimesInput,
      gen.PrimesResult> getListPrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListPrimeNumbers",
      requestType = gen.PrimesInput.class,
      responseType = gen.PrimesResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.PrimesInput,
      gen.PrimesResult> getListPrimeNumbersMethod() {
    io.grpc.MethodDescriptor<gen.PrimesInput, gen.PrimesResult> getListPrimeNumbersMethod;
    if ((getListPrimeNumbersMethod = PrimesServiceGrpc.getListPrimeNumbersMethod) == null) {
      synchronized (PrimesServiceGrpc.class) {
        if ((getListPrimeNumbersMethod = PrimesServiceGrpc.getListPrimeNumbersMethod) == null) {
          PrimesServiceGrpc.getListPrimeNumbersMethod = getListPrimeNumbersMethod =
              io.grpc.MethodDescriptor.<gen.PrimesInput, gen.PrimesResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListPrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimesInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.PrimesResult.getDefaultInstance()))
              .setSchemaDescriptor(new PrimesServiceMethodDescriptorSupplier("ListPrimeNumbers"))
              .build();
        }
      }
    }
    return getListPrimeNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PrimesServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimesServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimesServiceStub>() {
        @java.lang.Override
        public PrimesServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimesServiceStub(channel, callOptions);
        }
      };
    return PrimesServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PrimesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimesServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimesServiceBlockingStub>() {
        @java.lang.Override
        public PrimesServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimesServiceBlockingStub(channel, callOptions);
        }
      };
    return PrimesServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PrimesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrimesServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrimesServiceFutureStub>() {
        @java.lang.Override
        public PrimesServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrimesServiceFutureStub(channel, callOptions);
        }
      };
    return PrimesServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void streamPrimeNumbers(gen.PrimesInput request,
        io.grpc.stub.StreamObserver<gen.PrimesStreamResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamPrimeNumbersMethod(), responseObserver);
    }

    /**
     */
    default void listPrimeNumbers(gen.PrimesInput request,
        io.grpc.stub.StreamObserver<gen.PrimesResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPrimeNumbersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PrimesService.
   */
  public static abstract class PrimesServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PrimesServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PrimesService.
   */
  public static final class PrimesServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PrimesServiceStub> {
    private PrimesServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimesServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimesServiceStub(channel, callOptions);
    }

    /**
     */
    public void streamPrimeNumbers(gen.PrimesInput request,
        io.grpc.stub.StreamObserver<gen.PrimesStreamResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamPrimeNumbersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listPrimeNumbers(gen.PrimesInput request,
        io.grpc.stub.StreamObserver<gen.PrimesResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListPrimeNumbersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PrimesService.
   */
  public static final class PrimesServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PrimesServiceBlockingStub> {
    private PrimesServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimesServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimesServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<gen.PrimesStreamResult> streamPrimeNumbers(
        gen.PrimesInput request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamPrimeNumbersMethod(), getCallOptions(), request);
    }

    /**
     */
    public gen.PrimesResult listPrimeNumbers(gen.PrimesInput request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPrimeNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PrimesService.
   */
  public static final class PrimesServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PrimesServiceFutureStub> {
    private PrimesServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrimesServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrimesServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.PrimesResult> listPrimeNumbers(
        gen.PrimesInput request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListPrimeNumbersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STREAM_PRIME_NUMBERS = 0;
  private static final int METHODID_LIST_PRIME_NUMBERS = 1;

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
        case METHODID_STREAM_PRIME_NUMBERS:
          serviceImpl.streamPrimeNumbers((gen.PrimesInput) request,
              (io.grpc.stub.StreamObserver<gen.PrimesStreamResult>) responseObserver);
          break;
        case METHODID_LIST_PRIME_NUMBERS:
          serviceImpl.listPrimeNumbers((gen.PrimesInput) request,
              (io.grpc.stub.StreamObserver<gen.PrimesResult>) responseObserver);
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
          getStreamPrimeNumbersMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              gen.PrimesInput,
              gen.PrimesStreamResult>(
                service, METHODID_STREAM_PRIME_NUMBERS)))
        .addMethod(
          getListPrimeNumbersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.PrimesInput,
              gen.PrimesResult>(
                service, METHODID_LIST_PRIME_NUMBERS)))
        .build();
  }

  private static abstract class PrimesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PrimesServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.PrimesServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PrimesService");
    }
  }

  private static final class PrimesServiceFileDescriptorSupplier
      extends PrimesServiceBaseDescriptorSupplier {
    PrimesServiceFileDescriptorSupplier() {}
  }

  private static final class PrimesServiceMethodDescriptorSupplier
      extends PrimesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PrimesServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PrimesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PrimesServiceFileDescriptorSupplier())
              .addMethod(getStreamPrimeNumbersMethod())
              .addMethod(getListPrimeNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
