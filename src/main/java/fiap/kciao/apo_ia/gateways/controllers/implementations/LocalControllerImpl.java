package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.LocalController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudLocalImpl;
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
@RequestMapping("/local")
@Tag(name = "Local")
public class LocalControllerImpl implements LocalController {
    private final CrudLocalImpl crudLocalImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo local")
    @Override
    public ResponseEntity<LocalFullResponseDto> create(@RequestBody @Valid LocalCreateRequestDto localCreateRequestDto) {
        return ResponseEntity.ok(crudLocalImpl.create(localCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita um local")
    @Override
    public ResponseEntity<LocalFullResponseDto> update(@PathVariable String id, @RequestBody @Valid LocalUpdateRequestDto localUpdateRequestDto) {
        return ResponseEntity.ok(crudLocalImpl.update(id, localUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um local")
    @Override
    public ResponseEntity<LocalFullResponseDto> delete(@PathVariable String id) {
        crudLocalImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um local")
    @Override
    public ResponseEntity<LocalFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudLocalImpl.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos locais")
    @Override
    public ResponseEntity<List<LocalFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudLocalImpl.findAll());
    }
}
