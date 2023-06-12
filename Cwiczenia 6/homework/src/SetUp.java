import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static utils.Utlis.*;

public class SetUp {
    private final ConnectionFactory factory = new ConnectionFactory() {{setHost("localhost");}};
    private final Connection connection = factory.newConnection();
    private final Channel channel = connection.createChannel();

    public SetUp() throws IOException, TimeoutException {
    }

    private void createExchange(String name, BuiltinExchangeType type) throws IOException {
        channel.exchangeDeclare(name, type);
    }

    private void createQueue(String queueName) throws IOException {
        channel.queueDeclare(queueName, true, false, false, null);
    }

    private void bindQueueToExchange(String queueName, String exchangeName, String key) throws IOException {
        channel.queueBind(queueName, exchangeName, key);
    }

    public void setUp() throws IOException, TimeoutException {
        createExchange(ADMIN_EXCHANGE, BuiltinExchangeType.TOPIC);
        createExchange(ORDER_EXCHANGE, BuiltinExchangeType.TOPIC);
        createExchange(CONFIRM_EXCHANGE, BuiltinExchangeType.TOPIC);

        createQueue(PASSENGER_TRANSPORT_QUEUE);
        createQueue(CARGO_TRANSPORT_QUEUE);
        createQueue(SATELLITE_LAUNCH_QUEUE);
        createQueue(AGENCY_1_QUEUE);
        createQueue(AGENCY_2_QUEUE);
        createQueue(CARRIER_1_QUEUE);
        createQueue(CARRIER_2_QUEUE);
        createQueue(AUDIT_ORDER_QUEUE);
        createQueue(AUDIT_CONFIRM_QUEUE);

        bindQueueToExchange(PASSENGER_TRANSPORT_QUEUE, ORDER_EXCHANGE, PASSENGER_TRANSPORT_KEY);
        bindQueueToExchange(CARGO_TRANSPORT_QUEUE, ORDER_EXCHANGE, CARGO_TRANSPORT_KEY);
        bindQueueToExchange(SATELLITE_LAUNCH_QUEUE, ORDER_EXCHANGE, SATELLITE_LAUNCH_KEY);
        bindQueueToExchange(AGENCY_1_QUEUE, CONFIRM_EXCHANGE, CONFIRM_1_KEY);
        bindQueueToExchange(AGENCY_1_QUEUE, ADMIN_EXCHANGE, ADMIN_AGENCY_KEY);
        bindQueueToExchange(AGENCY_1_QUEUE, ADMIN_EXCHANGE, ADMIN_ALL_KEY);
        bindQueueToExchange(AGENCY_2_QUEUE, CONFIRM_EXCHANGE, CONFIRM_2_KEY);
        bindQueueToExchange(AGENCY_2_QUEUE, ADMIN_EXCHANGE, ADMIN_AGENCY_KEY);
        bindQueueToExchange(AGENCY_2_QUEUE, ADMIN_EXCHANGE, ADMIN_ALL_KEY);
        bindQueueToExchange(AUDIT_ORDER_QUEUE, ORDER_EXCHANGE, JOKER_KEY);
        bindQueueToExchange(AUDIT_CONFIRM_QUEUE, CONFIRM_EXCHANGE, JOKER_KEY);
        bindQueueToExchange(CARRIER_1_QUEUE, ADMIN_EXCHANGE, ADMIN_CARRIER_KEY);
        bindQueueToExchange(CARRIER_1_QUEUE, ADMIN_EXCHANGE, ADMIN_ALL_KEY);
        bindQueueToExchange(CARRIER_2_QUEUE, ADMIN_EXCHANGE, ADMIN_CARRIER_KEY);
        bindQueueToExchange(CARRIER_2_QUEUE, ADMIN_EXCHANGE, ADMIN_ALL_KEY);

        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Hello world!");
        SetUp setUp = new SetUp();
        setUp.setUp();
    }
}
