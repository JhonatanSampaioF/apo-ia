package fiap.kciao.apo_ia.usecases.services.setup;

import fiap.kciao.apo_ia.domains.*;
import fiap.kciao.apo_ia.usecases.services.query.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetupDatabase {
    private final UsuarioQueryService usuarioQueryService;
    private final PasswordEncoder passwordEncoder;
    private final LocalQueryService localQueryService;
    private final AbrigadoQueryService abrigadoQueryService;
    private final VoluntarioQueryService voluntarioQueryService;
    private final GrupoHabilidadeQueryService grupoHabilidadeQueryService;
    private final HabilidadeQueryService habilidadeQueryService;
    private final DoencaQueryService doencaQueryService;

    public void initializeDatabase() {
        criarUsuarioAdmin();
        criarLocal();
        criarGruposEHabilidades();
        List<Doenca> doencas = criarDoencas();
        criarAbrigadosEVoluntarios(doencas);
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
            log.info("Usuario ADMIN criado com sucesso.");
        } else {
            log.info("Usuario ADMIN ja existe.");
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
            log.info("Local criado.");
        } else {
            log.info("Local ja existe.");
        }
    }

    private void criarGruposEHabilidades() {
        if (grupoHabilidadeQueryService.findAll().isEmpty()) {
            Map<String, List<String>> grupos = new LinkedHashMap<>();
            grupos.put("Alimentação e Cozinha", Arrays.asList("Preparar refeições", "Higienizar utensílios e cozinha"));
            grupos.put("Cuidados com a Saúde", Arrays.asList("Cuidados médicos", "Auxiliar na enfermaria"));
            grupos.put("Apoio Emocional", Arrays.asList("Apoio psicológico basico", "Acolhimento infantil"));
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
            log.info("Grupos e habilidades criados.");
        } else {
            log.info("Grupos e habilidades ja existem.");
        }
    }

    private List<Doenca> criarDoencas() {
        if (doencaQueryService.findAll().isEmpty()) {
            List<Doenca> doencas = List.of(
                    Doenca.builder().nome("Gripe").gravidade("Leve").build(),
                    Doenca.builder().nome("Infecção").gravidade("Moderada").build(),
                    Doenca.builder().nome("Fratura").gravidade("Grave").build()
            );
            doencas = doencas.stream().map(doencaQueryService::save).toList();
            log.info("Doenças criadas.");
            return doencas;
        } else {
            log.info("Doenças já existem.");
            return doencaQueryService.findAll();
        }
    }

    private void criarAbrigadosEVoluntarios(List<Doenca> doencas) {
        if (abrigadoQueryService.findAll().isEmpty()) {
            String localId = localQueryService.findAll().get(0).getId();
            List<String> todasHabilidades = habilidadeQueryService.findAll().stream().map(Habilidade::getId).toList();

            for (int i = 1; i <= 14; i++) {
                boolean voluntario = i <= 7;

                List<String> doencaIds = new ArrayList<>();
                if (!voluntario && i % 2 == 0 && !doencas.isEmpty()) {
                    doencaIds.add(doencas.get(i % doencas.size()).getId());
                }

                Abrigado abrigado = Abrigado.builder()
                        .nome("Abrigado " + i)
                        .idade(20 + i)
                        .altura(1.6 + (i * 0.01))
                        .peso(60.0 + i)
                        .cpf("000.000.000-" + String.format("%02d", i))
                        .ferimento(i % 3 == 0 ? "Corte leve" : null)
                        .voluntario(voluntario)
                        .localId(localId)
                        .doencaIds(doencaIds)
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
            log.info("Abrigados e voluntarios criados.");
        } else {
            log.info("Abrigados e voluntarios ja existem.");
        }
    }

}
