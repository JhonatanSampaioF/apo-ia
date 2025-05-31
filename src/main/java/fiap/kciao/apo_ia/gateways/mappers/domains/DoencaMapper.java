package fiap.kciao.apo_ia.gateways.mappers.domains;

import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;

import java.util.List;

public class DoencaMapper {

    public static Doenca toEntityCreate(DoencaCreateRequestDto doencaCreateRequestDto) {
        return Doenca.builder()
                .nome(doencaCreateRequestDto.getNome())
                .gravidade(doencaCreateRequestDto.getGravidade())
                .build();
    }

    public static DoencaFullResponseDto toFullResponseDto(Doenca doenca) {
        return DoencaFullResponseDto.builder()
                .id(doenca.getId())
                .nome(doenca.getNome())
                .gravidade(doenca.getGravidade())
                .build();
    }

    public static List<DoencaFullResponseDto> toFullResponseDto(List<Doenca> doencas) {
        return doencas.stream().map(DoencaMapper::toFullResponseDto).toList();
    }
}
