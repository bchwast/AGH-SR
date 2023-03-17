package homework.client;

import java.io.BufferedReader;
import java.io.IOException;

public class TCPReceiver implements Runnable {

    private final BufferedReader in;

    public TCPReceiver(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                if (message.equals("EXIT")) {
                    System.exit(0);
                }
                System.out.println("Received TCP message: " + message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
