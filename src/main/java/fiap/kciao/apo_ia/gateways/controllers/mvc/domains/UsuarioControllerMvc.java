package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudUsuarioImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/usuario")
@RequiredArgsConstructor
public class UsuarioControllerMvc {

    private final CrudUsuarioImpl crudUsuario;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("usuarios", crudUsuario.findAll());
        return "domains/usuarios/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("usuario", new UsuarioCreateRequestDto());
        return "domains/usuarios/form";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid UsuarioCreateRequestDto dto) {
        crudUsuario.create(dto);
        return "redirect:/mvc/usuario";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        UsuarioFullResponseDto usuario = crudUsuario.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("usuario", UsuarioUpdateRequestDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build());
        return "domains/usuarios/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute @Valid UsuarioUpdateRequestDto dto) {
        crudUsuario.update(id, dto);
        return "redirect:/mvc/usuario";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudUsuario.delete(id);
        return "redirect:/mvc/usuario";
    }
}
