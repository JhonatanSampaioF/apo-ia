package fiap.kciao.apo_ia.usecases.domains.integration.queue;

import fiap.kciao.apo_ia.configs.RabbitMQConfig;
import fiap.kciao.apo_ia.usecases.services.query.UsuarioQueryService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableRabbit
@TestPropertySource(locations = "classpath:application-test.yml")
class InitializerQueueIntegrationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UsuarioQueryService usuarioQueryService;


    @Test
    void testInitializerQueueTriggersConsumer() {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                "start"
        );

        Awaitility.await().atMost(Duration.ofSeconds(10)).untilAsserted(() -> {
            // Verifique algum efeito direto, ex: verificar se o admin foi criado
            assertNotNull(usuarioQueryService.findByEmailOrNull("admin@email.com"));
        });
    }

}