package homework.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Client {

    private final String HOST_NAME = "localhost";
    private final int PORT_NUMBER = 22222;
    private final String MULTICAST_ADDRESS_NAME = "237.1.1.1";
    private final int MULTICAST_PORT_NUMBER = 33333;
    private final int BUFFER_LENGTH = 16384;
    private final Socket tcpSocket;
    private final DatagramSocket udpSocket;
    private final MulticastSocket multicastSocket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final TCPReceiver tcpReceiver;
    private final UDPReceiver udpReceiver;
    private final UDPReceiver multicastReceiver;
    private final Sender sender;

    public Client(int clientPort) throws IOException {
        InetAddress address = InetAddress.getByName(HOST_NAME);
        InetAddress multicastAddress = InetAddress.getByName(MULTICAST_ADDRESS_NAME);
        tcpSocket = new Socket(address, PORT_NUMBER, address, clientPort);
        in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        out = new PrintWriter(tcpSocket.getOutputStream(), true);

        udpSocket = new DatagramSocket();
        multicastSocket = new MulticastSocket(MULTICAST_PORT_NUMBER);
        multicastSocket.joinGroup(multicastAddress);

        sender = new Sender(out, address, PORT_NUMBER, udpSocket, multicastAddress, MULTICAST_PORT_NUMBER, multicastSocket);
        tcpReceiver = new TCPReceiver(in);
        udpReceiver = new UDPReceiver(BUFFER_LENGTH, udpSocket, false);
        multicastReceiver = new UDPReceiver(BUFFER_LENGTH, multicastSocket, true);
    }

    private void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> out.println("EXIT")));
        Thread senderThread = new Thread(sender);
        Thread tcpReceiverThread = new Thread(tcpReceiver);
        Thread udpReceiverThread = new Thread(udpReceiver);
        Thread multicastReceiverThread = new Thread(multicastReceiver);
        senderThread.start();
        tcpReceiverThread.start();
        udpReceiverThread.start();
        multicastReceiverThread.start();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("JAVA CLIENT on port: " + args[0]);
        Client client = new Client(Integer.parseInt(args[0]));
        client.run();
    }
}
