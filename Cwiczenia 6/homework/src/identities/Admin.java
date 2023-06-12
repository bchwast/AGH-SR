package identities;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static utils.Utlis.*;

public class Admin {
    private final ConnectionFactory factory = new ConnectionFactory() {{setHost("localhost");}};
    private final Connection connection = factory.newConnection();
    private final Channel channel = connection.createChannel();

    public Admin() throws IOException, TimeoutException {
    }

    private void receiveHandler(String queueName) throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received: " + message + ", with key: " + envelope.getRoutingKey());

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(queueName, false, consumer);
    }

    private void publishHandler(String message, String key) throws IOException {
        channel.basicPublish(ADMIN_EXCHANGE, key, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Sent: " + message + ", with key: " + key + ", to exchange: " + ADMIN_EXCHANGE);
    }

    private void printInfo() {
        System.out.println("Available receivers: agency, order, all");
        System.out.println("You can exit by typing: exit");
        System.out.println("You can get this info by typing: info");
    }

    public void run() throws IOException, TimeoutException {
        printInfo();
        receiveHandler(AUDIT_ORDER_QUEUE);
        receiveHandler(AUDIT_CONFIRM_QUEUE);

        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            System.out.println("Provide receiver name: ");
            String input = br.readLine();

            if (input.equals("info")) {
                printInfo();
                continue;
            }

            if (input.equals("exit")) {
                break;
            }

            String receiver = switch (input) {
                case "agency" -> ADMIN_AGENCY_KEY;
                case "order" -> ADMIN_CARRIER_KEY;
                case "all" -> ADMIN_ALL_KEY;
                default -> {
                    System.out.println("Wrong receiver name");
                    yield null;
                }
            };

            if (receiver == null) {
                continue;
            }

            System.out.println("Provide message: ");
            String message = br.readLine();
            if (message.equals("info")) {
                printInfo();
                continue;
            }
            if (message.equals("exit")) {
                break;
            }

            publishHandler(message, receiver);
        }

        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Admin admin = new Admin();
        admin.run();
    }
}
