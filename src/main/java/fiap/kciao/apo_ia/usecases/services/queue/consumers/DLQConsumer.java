package fiap.kciao.apo_ia.usecases.services.queue.consumers;

import fiap.kciao.apo_ia.configs.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DLQConsumer {

    @RabbitListener(queues = RabbitMQConfig.DLQ)
    public void handleDeadLetter(String payload) {
        log.warn("Mensagem movida para DLQ: {}", payload);
    }
}

