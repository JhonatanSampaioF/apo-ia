package fiap.kciao.apo_ia.usecases.services.queue.consumers;

import fiap.kciao.apo_ia.configs.RabbitMQConfig;
import fiap.kciao.apo_ia.usecases.services.setup.SetupDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitializerConsumer {

    private final SetupDatabase setupDatabase;
    private final Optional<CountDownLatch> initializerLatch;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(String payload) {
        try {
            log.info("Mensagem recebida para inicializacao: {}", payload);

            if ("erro".equalsIgnoreCase(payload)) {
                throw new IllegalArgumentException("Payload inválido forçado para teste de DLQ.");
            }

            setupDatabase.initializeDatabase();
            initializerLatch.ifPresent(CountDownLatch::countDown);
        } catch (Exception e) {
            log.error("Erro ao processar mensagem da fila '{}': {}", RabbitMQConfig.QUEUE, e.getMessage(), e);
            throw e;
        }
    }
}

