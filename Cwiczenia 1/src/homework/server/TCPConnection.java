package homework.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnection implements Runnable {

    private final Server server;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public TCPConnection(Socket socket) throws IOException {
        this.server = Server.getInstance();
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String username = in.readLine();
            server.addConnectionTCP(username, this);

            String message;
            while (true) {
                message = in.readLine();
                if (message.equals("EXIT")) {
                    out.println("EXIT");
                    break;
                }
                server.sendMessageTCP(username, "[" + username + "] " + message);
            }

            socket.close();
            server.removeConnectionTCP(username);
            server.removeConnectionUDP(username);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
