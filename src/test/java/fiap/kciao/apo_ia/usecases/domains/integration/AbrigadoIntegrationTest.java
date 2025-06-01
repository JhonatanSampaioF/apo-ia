package fiap.kciao.apo_ia.usecases.domains.integration;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AbrigadoIntegrationTest {

    @Autowired
    private AbrigadoQueryService abrigadoQueryService;
    @Autowired
    private LocalQueryService localQueryService;

    private String localId;

    @BeforeAll
    void setup() {
        Local local = Local.builder()
                .nome("Local Teste")
                .endereco("Rua X")
                .capacidade(100)
                .qtd_abrigados(0)
                .build();
        localId = localQueryService.save(local).getId();
    }

    @Test
    void testCreateAndFindAbrigado() {
        Abrigado abrigado = Abrigado.builder()
                .nome("Teste")
                .idade(25)
                .altura(1.70)
                .peso(70.0)
                .cpf("123.456.789-00")
                .ferimento("leve")
                .voluntario(false)
                .localId(localId)
                .build();

        Abrigado saved = abrigadoQueryService.save(abrigado);

        assertNotNull(saved.getId());
        assertEquals("Teste", abrigadoQueryService.findByIdOrThrow(saved.getId()).getNome());
    }
}
