package fiap.kciao.apo_ia.configs;

import fiap.kciao.apo_ia.domains.*;
import fiap.kciao.apo_ia.usecases.services.query.*;
import fiap.kciao.apo_ia.usecases.services.queue.producers.InitializerProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final InitializerProducer initializerProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        initializerProducer.sendInitMessage("start");
    }
}
