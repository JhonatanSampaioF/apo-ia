package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.VoluntarioController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudVoluntarioImpl;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voluntario")
@Tag(name = "Voluntário")
public class VoluntarioControllerImpl implements VoluntarioController {
    private final CrudVoluntarioImpl crudVoluntarioImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> create(@RequestBody @Valid VoluntarioCreateRequestDto voluntarioCreateRequestDto) {
        return ResponseEntity.ok(crudVoluntarioImpl.create(voluntarioCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita um voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> update(@PathVariable String id, @RequestBody @Valid VoluntarioUpdateRequestDto voluntarioUpdateRequestDto) {
        return ResponseEntity.ok(crudVoluntarioImpl.update(id, voluntarioUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> delete(@PathVariable String id) {
        crudVoluntarioImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudVoluntarioImpl.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos abrigados")
    @Override
    public ResponseEntity<List<VoluntarioFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudVoluntarioImpl.findAll());
    }

    @PostMapping("/gerenciar-habilidade")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Adicionar ou remove habilidade a um voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> manageHabilidade(@RequestParam String voluntarioId, @RequestParam String habilidadeId, @RequestParam ManageAction action) {
        return ResponseEntity.ok(crudVoluntarioImpl.manageHabilidade(voluntarioId, habilidadeId, action));
    }

    @PostMapping("/gerenciar-alocacao")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Adicionar ou remove alocação a um voluntário")
    @Override
    public ResponseEntity<VoluntarioFullResponseDto> manageAlocacao(@RequestParam String voluntarioId, @RequestParam String alocacaoId, @RequestParam ManageAction action) {
        return ResponseEntity.ok(crudVoluntarioImpl.manageAlocacao(voluntarioId, alocacaoId, action));
    }
}
