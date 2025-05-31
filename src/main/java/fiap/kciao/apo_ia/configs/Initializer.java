package fiap.kciao.apo_ia.configs;

import fiap.kciao.apo_ia.domains.*;
import fiap.kciao.apo_ia.usecases.services.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final UsuarioQueryService usuarioQueryService;
    private final PasswordEncoder passwordEncoder;
    private final LocalQueryService localQueryService;
    private final AbrigadoQueryService abrigadoQueryService;
    private final VoluntarioQueryService voluntarioQueryService;
    private final GrupoHabilidadeQueryService grupoHabilidadeQueryService;
    private final HabilidadeQueryService habilidadeQueryService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        criarUsuarioAdmin();
        criarLocal();
        criarGruposEHabilidades();
        criarAbrigadosEVoluntarios();
    }

    private void criarUsuarioAdmin() {
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

    private void criarLocal() {
        if (localQueryService.findAll().isEmpty()) {
            Local local = Local.builder()
                    .nome("Centro de Apoio Central")
                    .endereco("Rua da Solidariedade, 123")
                    .capacidade(100)
                    .qtd_abrigados(0)
                    .build();
            localQueryService.save(local);
            System.out.println("🟢 Local criado.");
        } else {
            System.out.println("🔵 Local já existe.");
        }
    }

    private void criarGruposEHabilidades() {
        if (grupoHabilidadeQueryService.findAll().isEmpty()) {
            Map<String, List<String>> grupos = new LinkedHashMap<>();
            grupos.put("Alimentação e Cozinha", Arrays.asList("Preparar refeições", "Higienizar utensílios e cozinha"));
            grupos.put("Cuidados com a Saúde", Arrays.asList("Cuidados médicos", "Auxiliar na enfermaria"));
            grupos.put("Apoio Emocional", Arrays.asList("Apoio psicológico básico", "Acolhimento infantil"));
            grupos.put("Higiene e Saneamento", Arrays.asList("Limpeza de ambientes", "Organização de kits de higiene"));
            grupos.put("Logística e Organização", Arrays.asList("Apoio administrativo", "Organizar estoques e doações", "Organizar roupas e itens pessoais", "Auxiliar em tarefas braçais (carregar e movimentar itens)"));

            for (var entry : grupos.entrySet()) {
                GrupoHabilidade grupo = GrupoHabilidade.builder().nome(entry.getKey()).build();
                grupo = grupoHabilidadeQueryService.save(grupo);

                int prioridade = 1;
                for (String habilidade : entry.getValue()) {
                    Habilidade nova = Habilidade.builder()
                            .nome(habilidade)
                            .prioridade(prioridade++)
                            .grupoHabilidadeId(grupo.getId())
                            .build();
                    habilidadeQueryService.save(nova);
                }
            }
            System.out.println("🟢 Grupos e habilidades criados.");
        } else {
            System.out.println("🔵 Grupos e habilidades já existem.");
        }
    }

    private void criarAbrigadosEVoluntarios() {
        if (abrigadoQueryService.findAll().isEmpty()) {
            String localId = localQueryService.findAll().get(0).getId();
            List<String> todasHabilidades = habilidadeQueryService.findAll().stream().map(Habilidade::getId).toList();

            for (int i = 1; i <= 14; i++) {
                boolean voluntario = i <= 7;
                Abrigado abrigado = Abrigado.builder()
                        .nome("Abrigado " + i)
                        .idade(20 + i)
                        .altura(1.6 + (i * 0.01))
                        .peso(60.0 + i)
                        .cpf("000.000.000-" + String.format("%02d", i))
                        .ferimento(i % 3 == 0 ? "Corte leve" : null)
                        .voluntario(voluntario)
                        .localId(localId)
                        .doencaIds(Collections.emptyList())
                        .build();
                abrigado = abrigadoQueryService.save(abrigado);

                if (voluntario) {
                    List<String> habilidadesAleatorias = todasHabilidades.subList(0, (i % todasHabilidades.size()) + 1);
                    Voluntario v = Voluntario.builder()
                            .abrigadoId(abrigado.getId())
                            .capacidade_motora("Normal")
                            .alocacao("Disponível")
                            .habilidadeIds(habilidadesAleatorias)
                            .build();
                    voluntarioQueryService.save(v);
                }
            }
            System.out.println("🟢 Abrigados e voluntários criados.");
        } else {
            System.out.println("🔵 Abrigados e voluntários já existem.");
        }
    }
}
