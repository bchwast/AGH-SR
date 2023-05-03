package server;

import gen.ArithmeticOpArguments;
import gen.ArithmeticOpResult;
import gen.CalculatorServiceGrpc.CalculatorServiceImplBase;
import gen.PrimeTesterArguments;
import gen.PrimeTesterResult;
import io.grpc.stub.StreamObserver;

import static server.Common.isPrime;

public class CalculatorServiceImpl extends CalculatorServiceImplBase {

    @Override
    public void operation(ArithmeticOpArguments request,
                          StreamObserver<ArithmeticOpResult> responseObserver) {
        System.out.println("multipleArgumentsRequest (" + request.getOpTypeValue() + ", #" + request.getArgsCount() + ")");

        if (request.getArgsCount() == 0) {
            System.out.println("No agruments");
        }

        double res = switch (request.getOpType()) {
            case SUM -> request.getArgsList().stream().mapToDouble(Double::doubleValue).sum();
            case AVG -> request.getArgsList().stream().mapToDouble(Double::doubleValue).average().orElse(0);
			case MULT -> request.getArgsList().stream().mapToDouble(Double::doubleValue).reduce(1, (a, b) -> a * b);
            case MIN -> request.getArgsList().stream().mapToDouble(Double::doubleValue).min().orElse(0);
            case MAX -> request.getArgsList().stream().mapToDouble(Double::doubleValue).max().orElse(0);
            case UNRECOGNIZED -> throw new IllegalStateException("Unexpected value: " + request.getOpType());
        };

        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(res).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void primeTester(PrimeTesterArguments request,
                            StreamObserver<PrimeTesterResult> responseObserver) {
        long val = request.getNumber();
        System.out.println("primeTester (" + val + ")");

        boolean res = isPrime(val);
        PrimeTesterResult result = PrimeTesterResult.newBuilder().setIsPrime(res).build();
        System.out.println("primeTester (" + val + ") = " + res);
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

}
