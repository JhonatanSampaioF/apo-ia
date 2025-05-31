package fiap.kciao.apo_ia.gateways.controllers.implementations;

import fiap.kciao.apo_ia.gateways.controllers.interfaces.UsuarioController;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudUsuarioImpl;
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
@RequestMapping("/usuario")
@Tag(name = "Usuário")
public class UsuarioControllerImpl implements UsuarioController {
    private final CrudUsuarioImpl crudUsuarioImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo usuário")
    @Override
    public ResponseEntity<UsuarioFullResponseDto> create(@RequestBody @Valid UsuarioCreateRequestDto usuarioCreateRequestDto) {
        return ResponseEntity.ok(crudUsuarioImpl.create(usuarioCreateRequestDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edita um usuário")
    @Override
    public ResponseEntity<UsuarioFullResponseDto> update(@PathVariable String id, @RequestBody @Valid UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        return ResponseEntity.ok(crudUsuarioImpl.update(id, usuarioUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um usuário")
    @Override
    public ResponseEntity<UsuarioFullResponseDto> delete(@PathVariable String id) {
        crudUsuarioImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um usuário")
    @Override
    public ResponseEntity<UsuarioFullResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(crudUsuarioImpl.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos usuários")
    @Override
    public ResponseEntity<List<UsuarioFullResponseDto>> findAll() {
        return ResponseEntity.ok(crudUsuarioImpl.findAll());
    }
}
