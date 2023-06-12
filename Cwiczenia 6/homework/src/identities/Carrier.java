package identities;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static utils.Utlis.*;

public class Carrier {
    private final ConnectionFactory factory = new ConnectionFactory() {{setHost("localhost");}};
    private final Connection connection = factory.newConnection();
    private final Channel channel = connection.createChannel();
    private final String queueName1;
    private final String queueName2;
    private final String carrierName;

    public Carrier(String carrierName, String queueName1, String queueName2) throws IOException, TimeoutException {
        this.carrierName = carrierName;
        this.queueName1 = queueName1;
        this.queueName2 = queueName2;
    }

    private void receiveHandler(String queueName) throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received: " + message + ", with key: " + envelope.getRoutingKey());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                channel.basicAck(envelope.getDeliveryTag(), false);

                String key = message.startsWith(AGENCY_1) ? CONFIRM_1_KEY : CONFIRM_2_KEY;
                String confirmation = "CONFIRMED by " + carrierName + ": " + message;
                channel.basicPublish(CONFIRM_EXCHANGE, key, null, confirmation.getBytes(StandardCharsets.UTF_8));
            }
        };

        channel.basicConsume(queueName, false, consumer);
    }

    private void receiveAdminHandler() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received: " + message + ", with key: " + envelope.getRoutingKey());

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(carrierName, false, consumer);
    }

    public void run() throws IOException {
        receiveHandler(queueName1);
        receiveHandler(queueName2);
        receiveAdminHandler();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Carrier carrier = new Carrier(args[0], args[1], args[2]);
        carrier.run();
    }
}
