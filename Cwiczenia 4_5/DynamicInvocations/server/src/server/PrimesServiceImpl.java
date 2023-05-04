package server;

import gen.PrimesInput;
import gen.PrimesResult;
import gen.PrimesServiceGrpc;
import gen.PrimesStreamResult;
import io.grpc.stub.StreamObserver;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class PrimesServiceImpl extends PrimesServiceGrpc.PrimesServiceImplBase {

    @Override
    public void streamPrimeNumbers(PrimesInput request, StreamObserver<PrimesStreamResult> responseObserver) {
        if (!request.hasField(PrimesInput.getDescriptor().findFieldByName("max"))) {
            System.out.println("generatePrimeNumbers (no max)");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("No max").asRuntimeException());
            return;
        }
        if (request.getMax() < 0) {
            System.out.println("generatePrimeNumbers (max=" + request.getMax() + ")");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("max must be positive").asRuntimeException());
            return;
        }
        System.out.println("generatePrimeNumbers is starting (max=" + request.getMax() + ")");
        LongStream.range(0, request.getMax())
                .filter(Common::isPrime)
                .mapToObj(value -> PrimesStreamResult.newBuilder().setValue(value).build())
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
        System.out.println("generatePrimeNumbers completed");
    }

    @Override
    public void listPrimeNumbers(PrimesInput request, StreamObserver<PrimesResult> responseObserver) {
        if (!request.hasField(PrimesInput.getDescriptor().findFieldByName("max"))) {
            System.out.println("getPrimeNumbers (no max)");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("No max").asRuntimeException());
            return;
        }
        if (request.getMax() < 0) {
            System.out.println("getPrimeNumbers (max=" + request.getMax() + ")");
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("max must be positive").asRuntimeException());
            return;
        }
        System.out.println("getPrimeNumbers is starting (max=" + request.getMax() + ")");
        List<Long> primes = new ArrayList<>();
        LongStream.range(0, request.getMax())
                .filter(Common::isPrime)
                .forEach(primes::add);
        PrimesResult primesResult = PrimesResult.newBuilder().addAllPrimes(primes).build();
        responseObserver.onNext(primesResult);
        responseObserver.onCompleted();
        System.out.println("getPrimeNumbers completed");
    }
}
