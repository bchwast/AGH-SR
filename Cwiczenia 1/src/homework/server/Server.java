package homework.server;

import homework.util.UDPTuple;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private final static int DEFAULT_PORT_NUMBER = 22222;
    public static Map<String, TCPConnection> tcpConnections = new ConcurrentHashMap<>();
    private static final Map<String, UDPTuple> udpConnections = new ConcurrentHashMap<>();
    private static Server instance = null;
    private final int portNumber;
    private final ServerSocket serverSocket;
    private UDPHandler udpHandler;

    private Server(int portNumber) throws IOException {
        this.portNumber = portNumber;
        this.serverSocket = new ServerSocket(portNumber);

    }
    public static Server getInstance() throws IOException {
        if (instance == null) {
            instance = new Server(DEFAULT_PORT_NUMBER);
        }
        return instance;
    }

    private void run() throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sendMessageTCP(null, "EXIT")));
        Thread listener = new Thread(() -> {
            try {
                System.out.println("JAVA SERVER on port: " + portNumber);

                while (true) {
                    Socket socket = serverSocket.accept();
                    TCPConnection tcpConnection = new TCPConnection(socket);
                    Thread tcpConnectionThread = new Thread(tcpConnection);
                    tcpConnectionThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listener.start();

        DatagramSocket datagramSocket = new DatagramSocket(portNumber);
        udpHandler = new UDPHandler(datagramSocket);
        Thread udpHandlerThread = new Thread(udpHandler);
        udpHandlerThread.start();
    }

    public void sendMessageTCP(String username, String message) {
        tcpConnections.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(username))
                .forEach(entry -> entry.getValue().sendMessage(message));
    }

    public void sendMessageUDP(UDPTuple udpTuple, String message) {
        udpConnections.entrySet().stream()
                .filter(entry -> entry.getValue().port() != udpTuple.port() || !entry.getValue().address().equals(udpTuple.address()))
                .forEach(entry -> {
                    try {
                        udpHandler.sendMessage(entry.getValue(), message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void addConnectionTCP(String username, TCPConnection connection) {
        tcpConnections.put(username, connection);
        System.out.println("TCP connected " + username);
    }

    public void addConnectionUDP(String username, UDPTuple udpTuple) {
        udpConnections.put(username, udpTuple);
        System.out.println("UDP connected " + username);
    }

    public void removeConnectionTCP(String username) {
        tcpConnections.remove(username);
        System.out.println("TCP disconnected " + username);
    }

    public void removeConnectionUDP(String username) {
        if (udpConnections.containsKey(username)) {
            udpConnections.remove(username);
            System.out.println("UDP disconnected " + username);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = getInstance();
        server.run();
    }
}
