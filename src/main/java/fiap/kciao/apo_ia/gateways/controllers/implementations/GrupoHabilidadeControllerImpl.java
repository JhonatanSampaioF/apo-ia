package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.GrupoHabilidadeController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudGrupoHabilidadeImpl;
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
@RequestMapping("/grupo-habilidade")
@Tag(name = "Grupo Habilidade")
public class GrupoHabilidadeControllerImpl implements GrupoHabilidadeController {
    private final CrudGrupoHabilidadeImpl crudGrupoHabilidadeImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo grupo de habilidade")
    @Override
    public ResponseEntity<GrupoHabilidadeFullResponseDto> create(@RequestBody @Valid GrupoHabilidadeCreateRequestDto grupoHabilidadeCreateRequestDto) {
        return ResponseEntity.ok(crudGrupoHabilidadeImpl.create(grupoHabilidadeCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita um grupo de habilidade")
    @Override
    public ResponseEntity<GrupoHabilidadeFullResponseDto> update(@PathVariable String id, @RequestBody @Valid GrupoHabilidadeUpdateRequestDto grupoHabilidadeUpdateRequestDto) {
        return ResponseEntity.ok(crudGrupoHabilidadeImpl.update(id, grupoHabilidadeUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um grupo de habilidade")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        crudGrupoHabilidadeImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um grupo de habilidade")
    @Override
    public ResponseEntity<GrupoHabilidadeFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudGrupoHabilidadeImpl.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos grupos de habilidades")
    @Override
    public ResponseEntity<List<GrupoHabilidadeFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudGrupoHabilidadeImpl.findAll());
    }
}
