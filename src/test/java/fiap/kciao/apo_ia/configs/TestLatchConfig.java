package fiap.kciao.apo_ia.configs;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
@TestConfiguration
public class TestLatchConfig {

    @Bean
    public CountDownLatch initializerLatch() {
        return new CountDownLatch(1);
    }
}

