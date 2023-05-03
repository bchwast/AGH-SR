package server;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final String address = "127.0.0.5";
    private final int port = 50051;
    private io.grpc.Server server;

    private SocketAddress socket;

    private void start() throws IOException {
        try {
            socket = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch (UnknownHostException e) {
        }

        //You will want to employ flow-control so that the queue doesn't blow up your memory. You can cast StreamObserver to CallStreamObserver to get flow-control API
        server = NettyServerBuilder.forAddress(socket).executor(Executors.newFixedThreadPool(6))
                .addService(ProtoReflectionService.newInstance())
                .addService(new CalculatorServiceImpl())
                .addService(new PrimesServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            Server.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }

}
