package fiap.kciao.apo_ia.gateways.mappers.domains;

import fiap.kciao.apo_ia.domains.Voluntario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;

import java.util.List;

public class VoluntarioMapper {
    public static Voluntario toEntityCreate(VoluntarioCreateRequestDto voluntarioCreateRequestDto) {
        return Voluntario.builder()
                .capacidade_motora(voluntarioCreateRequestDto.getCapacidade_motora())
                .build();
    }

    public static VoluntarioFullResponseDto toFullResponseDto(Voluntario voluntario) {
        return VoluntarioFullResponseDto.builder()
                .id(voluntario.getId())
                .alocacao(voluntario.getAlocacao())
                .capacidade_motora(voluntario.getCapacidade_motora())
                .habilidadeIds(voluntario.getHabilidadeIds())
                .abrigadoId(voluntario.getAbrigadoId())
                .build();
    }

    public static List<VoluntarioFullResponseDto> toFullResponseDto(List<Voluntario> voluntarios) {
        return voluntarios.stream().map(VoluntarioMapper::toFullResponseDto).toList();
    }
}
