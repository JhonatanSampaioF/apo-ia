package fiap.kciao.apo_ia.configs;

import fiap.kciao.apo_ia.domains.*;
import fiap.kciao.apo_ia.usecases.services.query.*;
import fiap.kciao.apo_ia.usecases.services.queue.producers.InitializerProducer;
import fiap.kciao.apo_ia.usecases.services.setup.SetupDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class Initializer {

    private final SetupDatabase setupDatabase;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        setupDatabase.initializeDatabase();
    }
}
