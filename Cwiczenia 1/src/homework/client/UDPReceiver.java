package homework.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPReceiver implements Runnable {

    private final DatagramPacket packet;
    private final DatagramSocket socket;
    private final boolean multicast;
    private final byte[] buffer;

    public UDPReceiver(int bufferLength, DatagramSocket socket, boolean multicast) {
        buffer = new byte[bufferLength];
        this.packet = new DatagramPacket(buffer, bufferLength);
        this.socket = socket;
        this.multicast = multicast;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Arrays.fill(buffer, (byte) 0);
                socket.receive(packet);
                String message = new String(packet.getData());
                System.out.println("Received " + (multicast ? "Multicast" : "UDP") + " message: " + message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
