package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudLocalImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/local")
@RequiredArgsConstructor
public class LocalControllerMvc {

    private final CrudLocalImpl crudLocal;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("locais", crudLocal.findAll());
        return "domains/locais/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("local", new LocalCreateRequestDto());
        return "domains/locais/form";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid LocalCreateRequestDto dto) {
        crudLocal.create(dto);
        return "redirect:/mvc/local";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        LocalFullResponseDto local = crudLocal.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("local", LocalUpdateRequestDto.builder()
                .nome(local.getNome())
                .endereco(local.getEndereco())
                .capacidade(local.getCapacidade())
                .qtd_abrigados(local.getQtd_abrigados())
                .build());
        return "domains/locais/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute @Valid LocalUpdateRequestDto dto) {
        crudLocal.update(id, dto);
        return "redirect:/mvc/local";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudLocal.delete(id);
        return "redirect:/mvc/local";
    }
}
