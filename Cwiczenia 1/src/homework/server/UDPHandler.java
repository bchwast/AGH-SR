package homework.server;

import homework.util.UDPTuple;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPHandler implements Runnable {

    private final int BUFFER_LENGTH = 16384;
    private final String HELLO_MESSAGE = "!!!HELLO!!!";
    private final DatagramSocket socket;
    private final Server server;

    public UDPHandler(DatagramSocket socket) throws IOException {
        this.socket = socket;
        this.server = Server.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[BUFFER_LENGTH];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String message = new String(packet.getData());

                UDPTuple udpTuple = new UDPTuple(address, port);
                if (message.contains(HELLO_MESSAGE)) {
                    String username = StringUtils.substringBetween(message, HELLO_MESSAGE, HELLO_MESSAGE);
                    server.addConnectionUDP(username, udpTuple);
                } else {
                    server.sendMessageUDP(udpTuple, message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(UDPTuple udpTuple, String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, udpTuple.address(), udpTuple.port());
        socket.send(packet);
    }
}