package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;

import java.util.List;

public interface CrudDoenca {
    public DoencaFullResponseDto create(DoencaCreateRequestDto doencaCreateRequestDto);
    public DoencaFullResponseDto update(String id, DoencaUpdateRequestDto doencaUpdateRequestDto);
    public DoencaFullResponseDto findById(String id);
    public List<DoencaFullResponseDto> findAll();
    public void delete(String id);
}
