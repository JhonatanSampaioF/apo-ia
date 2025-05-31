package fiap.kciao.apo_ia.gateways.mappers.domains;

import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;

import java.util.List;

public class HabilidadeMapper {

    public static Habilidade toEntityCreate(HabilidadeCreateRequestDto habilidadeCreateRequestDto) {
        return Habilidade.builder()
                .nome(habilidadeCreateRequestDto.getNome())
                .prioridade(habilidadeCreateRequestDto.getPrioridade())
                .build();
    }

    public static HabilidadeFullResponseDto toFullResponseDto(Habilidade habilidade) {
        return HabilidadeFullResponseDto.builder()
                .id(habilidade.getId())
                .nome(habilidade.getNome())
                .prioridade(habilidade.getPrioridade())
                .grupoHabilidadeId(habilidade.getGrupoHabilidadeId())
                .build();
    }

    public static List<HabilidadeFullResponseDto> toFullResponseDto(List<Habilidade> habilidades) {
        return habilidades.stream().map(HabilidadeMapper::toFullResponseDto).toList();
    }
}
