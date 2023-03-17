package homework.client;

import homework.util.Ye;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Sender implements Runnable {

    private final String HELLO_MESSAGE = "!!!HELLO!!!";
    private final PrintWriter out;
    private final Scanner scanner = new Scanner(System.in);
    private final InetAddress address;
    private final InetAddress multicastAddress;
    private final int port;
    private final int multicastPort;
    private final DatagramSocket socketUDP;
    private final MulticastSocket multicastSocket;

    public Sender(PrintWriter out, InetAddress address, int port, DatagramSocket socketUDP,
                  InetAddress multicastAddress, int multicastPort, MulticastSocket multicastSocket) {
        this.out = out;
        this.address = address;
        this.port = port;
        this.socketUDP = socketUDP;
        this.multicastAddress = multicastAddress;
        this.multicastPort = multicastPort;
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        out.println(username);

        byte[] helloBuffer = (HELLO_MESSAGE + username + HELLO_MESSAGE).getBytes();
        DatagramPacket helloPacket = new DatagramPacket(helloBuffer, helloBuffer.length, address, port);
        try {
            socketUDP.send(helloPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message;
        while (true) {
            message = scanner.nextLine();

            switch (message) {
                case "EXIT" -> {
                    out.println(message);
                    System.exit(0);
                }
                case "U" -> {
                    message = "[" + username + "] " + Ye.ye;
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                    try {
                        socketUDP.send(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "M" -> {
                    message = "[" + username + "] " + Ye.ye;
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastAddress, multicastPort);
                    try {
                        multicastSocket.send(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                default -> out.println(message);
            }
        }

    }
}
