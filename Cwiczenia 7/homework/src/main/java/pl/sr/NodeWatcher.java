package pl.sr;

import org.apache.zookeeper.*;

import java.io.IOException;

public class NodeWatcher implements Watcher {

    private final String Z_NODE = "/z";
    private final ZooKeeper zooKeeper;
    private final String processName;
    private Process process;
    private boolean isRunning = false;

    public NodeWatcher(String ipPort, String processName) throws IOException, InterruptedException, KeeperException {
        this.processName = processName;
        this.zooKeeper = new ZooKeeper(ipPort, 3000, this);
        this.zooKeeper.addWatch("/", AddWatchMode.PERSISTENT_RECURSIVE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case NodeCreated -> {
                if (watchedEvent.getPath().equals(Z_NODE)) {
                    startProcess();
                } else if (watchedEvent.getPath().startsWith(Z_NODE)) {
                    System.out.println("Number of nodes: " + getNumberOfNodes());
                }
                System.out.println("Created node: " + watchedEvent.getPath());
            }
            case NodeDeleted -> {
                if (watchedEvent.getPath().equals(Z_NODE)) {
                    killProcess();
                }
                System.out.println("Deleted node: " + watchedEvent.getPath());
            }
        }
    }

    public void printTree(String nodePath, int level) throws InterruptedException, KeeperException {
        if (zooKeeper.exists(nodePath, false) == null) {
            System.out.println("'/z' Node does not exist");
            return;
        }
        System.out.print("   ".repeat(level));
        System.out.println(nodePath);
        zooKeeper.getChildren(nodePath, false)
                .forEach(child -> {
                    try {
                        printTree(String.join("/", nodePath, child), level + 1);
                    } catch (InterruptedException | KeeperException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private int getNumberOfNodes() {
        try {
            if (zooKeeper.exists(Z_NODE, true) != null) {
                return zooKeeper.getAllChildrenNumber(Z_NODE);
            }
        } catch (KeeperException | InterruptedException e) {
            System.out.println("Error while getting number of nodes");
            e.printStackTrace();
        }
        return 0;
    }

    private void startProcess() {
        System.out.println("Starting process: " + processName);
        if (!isRunning) {
            try {
                process = Runtime.getRuntime().exec(processName);
                isRunning = true;
            } catch (IOException e) {
                System.out.println("Error while starting process: " + processName);
                e.printStackTrace();
            }
        }
    }

    private void killProcess() {
        if (isRunning) {
            System.out.println("Killing process: " + processName);
            process.destroy();
            isRunning = false;
        }
    }
}
