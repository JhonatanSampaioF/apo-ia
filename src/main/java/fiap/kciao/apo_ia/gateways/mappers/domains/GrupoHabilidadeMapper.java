package fiap.kciao.apo_ia.gateways.mappers.domains;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;

import java.util.List;

public class GrupoHabilidadeMapper {

    public static GrupoHabilidade toEntityCreate(GrupoHabilidadeCreateRequestDto habilidadeCreateRequestDto) {
        return GrupoHabilidade.builder()
                .nome(habilidadeCreateRequestDto.getNome())
                .build();
    }

    public static GrupoHabilidadeFullResponseDto toFullResponseDto(GrupoHabilidade grupoHabilidade) {
        return GrupoHabilidadeFullResponseDto.builder()
                .id(grupoHabilidade.getId())
                .nome(grupoHabilidade.getNome())
                .build();
    }

    public static List<GrupoHabilidadeFullResponseDto> toFullResponseDto(List<GrupoHabilidade> gruposHabilidades) {
        return gruposHabilidades.stream().map(GrupoHabilidadeMapper::toFullResponseDto).toList();
    }
}
