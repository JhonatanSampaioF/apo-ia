package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.AbrigadoController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudAbrigado;
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
@RequestMapping("/abrigado")
@Tag(name = "Abrigado")
public class AbrigadoControllerImpl implements AbrigadoController {
    private final CrudAbrigado crudAbrigado;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo abrigado")
    @Override
    public ResponseEntity<AbrigadoFullResponseDto> save(@RequestBody @Valid AbrigadoCreateRequestDto abrigadoCreateRequestDto) {
        return ResponseEntity.ok(crudAbrigado.create(abrigadoCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita um abrigado")
    @Override
    public ResponseEntity<AbrigadoFullResponseDto> update(@PathVariable String id, @RequestBody @Valid AbrigadoUpdateRequestDto abrigadoUpdateRequestDto) {
        return ResponseEntity.ok(crudAbrigado.update(id, abrigadoUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um abrigado")
    @Override
    public ResponseEntity<AbrigadoFullResponseDto> delete(@PathVariable String id) {
        crudAbrigado.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um abrigado")
    @Override
    public ResponseEntity<AbrigadoFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudAbrigado.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos abrigados")
    @Override
    public ResponseEntity<List<AbrigadoFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudAbrigado.findAll());
    }

    @PostMapping("/gerenciar-doenca")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Adicionar ou remove doença a um abrigado")
    @Override
    public ResponseEntity<AbrigadoFullResponseDto> manageDoenca(@RequestParam String doencaId, @RequestParam String abrigadoId, @RequestParam ManageAction action) {
        return ResponseEntity.ok(crudAbrigado.manageDoenca(doencaId, abrigadoId, action));
    }
}
