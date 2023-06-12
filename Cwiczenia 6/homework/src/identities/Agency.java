package identities;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static utils.Utlis.*;

public class Agency {
    private final ConnectionFactory factory = new ConnectionFactory() {{setHost("localhost");}};
    private final Connection connection = factory.newConnection();
    private final Channel channel = connection.createChannel();
    private final String agencyTag;
    private int currentNumber = 0;

    public Agency(String agencyName) throws IOException, TimeoutException {
        this.agencyTag = agencyName.equals(AGENCY_1) ? AGENCY_1 : AGENCY_2;
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
        channel.basicPublish(ORDER_EXCHANGE, key, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Sent: " + message + ", with key: " + key + ", to exchange: " + ORDER_EXCHANGE);
    }

    private void printInfo() {
        System.out.println("Available order types: " + PASSENGER_TRANSPORT_KEY + ", " + CARGO_TRANSPORT_KEY + ", " + SATELLITE_LAUNCH_KEY);
        System.out.println("You can exit by typing: exit");
        System.out.println("You can get this info by typing: info");
    }

    private String createMessage(String payload) {
        return agencyTag + ":" + currentNumber++ + " { " + payload + " }";
    }

    public void run() throws IOException, TimeoutException {
        printInfo();
        receiveHandler(agencyTag);

        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            System.out.println("Provide order type: ");
            String input = br.readLine();

            if (input.equals("info")) {
                printInfo();
                continue;
            }

            if (input.equals("exit")) {
                break;
            }

            if (!input.equals(PASSENGER_TRANSPORT_KEY) && !input.equals(CARGO_TRANSPORT_KEY) && !input.equals(SATELLITE_LAUNCH_KEY)) {
                System.out.println("Wrong order type!");
                continue;
            }

            System.out.println("Provide payload: ");
            String payload = br.readLine();
            if (payload.equals("info")) {
                printInfo();
                continue;
            }
            if (payload.equals("exit")) {
                break;
            }

            publishHandler(createMessage(payload), input);
        }

        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Agency agency = new Agency(args[0]);
        agency.run();
    }
}
