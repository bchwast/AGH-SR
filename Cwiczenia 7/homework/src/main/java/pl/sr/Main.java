package pl.sr;

import org.apache.zookeeper.KeeperException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        if (args.length < 2) {
            System.out.println("Required 2 arguments: <IP:PORT> <PROCESS NAME>");
            System.exit(1);
        }

        String ipPort = args[0];
        String processName = args[1];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in;
        NodeWatcher nodeWatcher = new NodeWatcher(ipPort, processName);

        System.out.println("Enter 'getTree' to get tree of nodes or 'exit' to exit");
        while (true) {
            in = reader.readLine();
            switch (in) {
                case "getTree" -> nodeWatcher.printTree("/z", 0);
                case "exit" -> System.exit(0);
                default -> System.out.println("Unknown command, enter 'getTree' to get tree of nodes or 'exit' to exit");
            }
        }
    }
}
