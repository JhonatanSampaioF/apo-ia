package fiap.kciao.apo_ia.configs;

import fiap.kciao.apo_ia.usecases.services.setup.SetupDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Initializer {

    private final SetupDatabase setupDatabase;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        setupDatabase.initializeDatabase();
    }
}
