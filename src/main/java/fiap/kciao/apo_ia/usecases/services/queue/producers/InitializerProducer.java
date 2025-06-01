package fiap.kciao.apo_ia.usecases.services.queue.producers;

import fiap.kciao.apo_ia.configs.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InitializerProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendInitMessage(String payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                payload
        );
    }
}

