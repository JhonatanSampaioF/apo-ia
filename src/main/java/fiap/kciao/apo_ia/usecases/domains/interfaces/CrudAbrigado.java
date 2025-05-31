package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;

import java.util.List;

public interface CrudAbrigado {
    public AbrigadoFullResponseDto create(AbrigadoCreateRequestDto abrigadoCreateRequestDto);
    public AbrigadoFullResponseDto update(String id, AbrigadoUpdateRequestDto abrigadoUpdateRequestDto);
    public AbrigadoFullResponseDto findById(String id);
    public List<AbrigadoFullResponseDto> findAll();
    public void delete(String id);
    public AbrigadoFullResponseDto manageDoenca(String doencaId, String abrigadoId, ManageAction action);
}
