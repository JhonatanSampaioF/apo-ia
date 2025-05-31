package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.HabilidadeController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudHabilidadeImpl;
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
@RequestMapping("/habilidade")
@Tag(name = "Habilidade")
public class HabilidadeControllerImpl implements HabilidadeController {
    private final CrudHabilidadeImpl crudHabilidadeImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um nova habilidade")
    @Override
    public ResponseEntity<HabilidadeFullResponseDto> create(@RequestBody @Valid HabilidadeCreateRequestDto habilidadeCreateRequestDto) {
        return ResponseEntity.ok(crudHabilidadeImpl.create(habilidadeCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita uma habilidade")
    @Override
    public ResponseEntity<HabilidadeFullResponseDto> update(@PathVariable String id, @RequestBody @Valid HabilidadeUpdateRequestDto habilidadeUpdateRequestDto) {
        return ResponseEntity.ok(crudHabilidadeImpl.update(id, habilidadeUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta uma habilidade")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        crudHabilidadeImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma habilidade")
    @Override
    public ResponseEntity<HabilidadeFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudHabilidadeImpl.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos abrigados")
    @Override
    public ResponseEntity<List<HabilidadeFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudHabilidadeImpl.findAll());
    }
}
