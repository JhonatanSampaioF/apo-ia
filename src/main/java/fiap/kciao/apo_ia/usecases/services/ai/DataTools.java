package fiap.kciao.apo_ia.usecases.services.ai;

import fiap.kciao.apo_ia.domains.*;
import fiap.kciao.apo_ia.usecases.services.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTools {

    private final AbrigadoQueryService abrigadoQueryService;
    private final LocalQueryService localQueryService;
    private final DoencaQueryService doencaQueryService;
    private final VoluntarioQueryService voluntarioQueryService;
    private final HabilidadeQueryService habilidadeQueryService;
    private final GrupoHabilidadeQueryService grupoHabilidadeQueryService;

    @Tool(description = "Obtem os dados do local, abrigados e suas doenças e ferimentos, voluntários com suas habilidades")
    public String getContext() {
        Local local = localQueryService.findAll().stream().findFirst().orElse(null);
        if (local == null) return "Nenhum local encontrado.";

        List<Abrigado> abrigados = abrigadoQueryService.findAllByLocalId(local.getId());
        StringBuilder context = new StringBuilder();

        context.append("Local: ").append(local.getNome()).append(" (").append(local.getEndereco()).append(")\n");
        context.append("Capacidade: ").append(local.getCapacidade()).append(", Abrigados atuais: ").append(local.getQtd_abrigados()).append("\n\n");

        for (Abrigado abrigado : abrigados) {
            context.append("- Abrigado: ").append(abrigado.getNome()).append(" (").append(abrigado.getIdade()).append(" anos)\n");
            context.append("  Ferimento: ").append(abrigado.getFerimento()).append("\n");

            if (abrigado.getDoencaIds() != null) {
                for (String doencaId : abrigado.getDoencaIds()) {
                    Doenca doenca = doencaQueryService.findByIdOrNull(doencaId);
                    if (doenca != null) {
                        context.append("  Doença: ").append(doenca.getNome()).append(" (Gravidade: ").append(doenca.getGravidade()).append(")\n");
                    }
                }
            }

            if (Boolean.TRUE.equals(abrigado.getVoluntario())) {
                context.append("  Voluntário\n");

                Voluntario voluntario = voluntarioQueryService.findByAbrigadoIdOrElseNull(abrigado.getId());
                if (voluntario != null && voluntario.getHabilidadeIds() != null) {
                    for (String habilidadeId : voluntario.getHabilidadeIds()) {
                        Habilidade habilidade = habilidadeQueryService.findByIdOrElseNull(habilidadeId);
                        if (habilidade != null) {
                            GrupoHabilidade grupoHabilidade = grupoHabilidadeQueryService.findByIdOrElseNull(habilidade.getGrupoHabilidadeId());
                            context.append("    Habilidade: ").append(habilidade.getNome());
                            if (grupoHabilidade != null) context.append(" (Grupo: ").append(grupoHabilidade.getNome()).append(")");
                            context.append("\n");
                        }
                    }
                }
            }

            context.append("\n");
        }

        context.append("Com base nas habilidades dos voluntários, sugira uma alocação por local considerando a gravidade das doenças e os grupos de habilidades.");
        return context.toString();
    }
}
