package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;

import java.util.List;

public interface CrudVoluntario {
    public VoluntarioFullResponseDto create(VoluntarioCreateRequestDto voluntarioCreateRequestDto);
    public VoluntarioFullResponseDto update(String id, VoluntarioUpdateRequestDto voluntarioUpdateRequestDto);
    public VoluntarioFullResponseDto findById(String id);
    public List<VoluntarioFullResponseDto> findAll();
    public void delete(String id);
    public VoluntarioFullResponseDto manageHabilidade(String voluntarioId, String habilidadeId, ManageAction action);
    public VoluntarioFullResponseDto manageAlocacao(String voluntarioId, String alocacao, ManageAction action);
    VoluntarioFullResponseDto findByAbrigadoId(String id);
}
