package fiap.kciao.apo_ia.configs;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "initializer-queue";
    public static final String DLQ = "initializer-queue.dlq";
    public static final String EXCHANGE = "apoia-exchange";
    public static final String ROUTING_KEY = "apoia.routingKey";
    public static final String DLQ_ROUTING_KEY = "apoia.routingKey.dlq";

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ);
    }

    @Bean
    public Queue initializerQueue() {
        return QueueBuilder.durable(QUEUE)
                .withArgument("x-dead-letter-exchange", EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(initializerQueue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(exchange()).with(DLQ_ROUTING_KEY);
    }
}