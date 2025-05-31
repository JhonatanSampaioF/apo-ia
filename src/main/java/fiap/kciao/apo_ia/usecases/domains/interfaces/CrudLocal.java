package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;

import java.util.List;

public interface CrudLocal {
    public LocalFullResponseDto create(LocalCreateRequestDto localCreateRequestDto);
    public LocalFullResponseDto update(String id, LocalUpdateRequestDto localUpdateRequestDto);
    public List<LocalFullResponseDto> findAll();
    public LocalFullResponseDto findById(String id);
    public void delete(String id);
}
