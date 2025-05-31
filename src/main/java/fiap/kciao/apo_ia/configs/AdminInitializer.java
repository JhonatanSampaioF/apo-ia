package fiap.kciao.apo_ia.configs;

import fiap.kciao.apo_ia.domains.Usuario;
import fiap.kciao.apo_ia.gateways.repositories.UsuarioRepository;
import fiap.kciao.apo_ia.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UsuarioQueryService usuarioQueryService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void createAdminUserIfNotExists() {
        String emailAdmin = "admin@email.com";
        if (usuarioQueryService.findByEmailOrNull(emailAdmin) == null) {
            Usuario admin = Usuario.builder()
                    .nome("Admin")
                    .email(emailAdmin)
                    .senha(passwordEncoder.encode("admin"))
                    .build();

            usuarioQueryService.save(admin);
            System.out.println("🟢 Usuário ADMIN criado com sucesso.");
        } else {
            System.out.println("🔵 Usuário ADMIN já existe.");
        }
    }
}