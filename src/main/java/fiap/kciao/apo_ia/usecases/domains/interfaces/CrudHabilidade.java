package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;

import java.util.List;

public interface CrudHabilidade {
    public HabilidadeFullResponseDto create(HabilidadeCreateRequestDto habilidadeCreateRequestDto);
    public HabilidadeFullResponseDto update(String id, HabilidadeUpdateRequestDto habilidadeUpdateRequestDto);
    public HabilidadeFullResponseDto findById(String id);
    public List<HabilidadeFullResponseDto> findAll();
    public void delete(String id);
    List<HabilidadeFullResponseDto> findByGroupId(String id);
}
