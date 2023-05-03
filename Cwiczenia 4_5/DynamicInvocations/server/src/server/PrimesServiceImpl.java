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
