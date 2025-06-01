package fiap.kciao.apo_ia.gateways.controllers.mvc.domains.doenca;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudDoencaImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/doenca")
@RequiredArgsConstructor
public class DoencaControllerMvc {

    private final CrudDoencaImpl crudDoenca;

    // LISTAGEM
    @GetMapping
    public String list(Model model) {
        List<DoencaFullResponseDto> doencas = crudDoenca.findAll();
        model.addAttribute("doencas", doencas);
        return "domains/doencas/list";
    }

    // FORMULÁRIO DE NOVA DOENÇA
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("doenca", new DoencaCreateRequestDto());
        return "domains/doencas/form";
    }

    // FORMULÁRIO DE EDIÇÃO
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        DoencaFullResponseDto existing = crudDoenca.findById(id);
        DoencaUpdateRequestDto dto = DoencaUpdateRequestDto.builder()
                .nome(existing.getNome())
                .gravidade(existing.getGravidade())
                .build();
        model.addAttribute("doenca", dto);
        model.addAttribute("id", id);
        return "domains/doencas/form";
    }

    // CRIAÇÃO
    @PostMapping
    public String create(@ModelAttribute("doenca") @Valid DoencaCreateRequestDto dto) {
        crudDoenca.create(dto);
        return "redirect:/mvc/doenca";
    }

    // EDIÇÃO
    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute("doenca") @Valid DoencaUpdateRequestDto dto) {
        crudDoenca.update(id, dto);
        return "redirect:/mvc/doenca";
    }

    // DETALHES
    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        DoencaFullResponseDto doenca = crudDoenca.findById(id);
        model.addAttribute("doenca", doenca);
        return "domains/doencas/detail";
    }

    // EXCLUSÃO
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudDoenca.delete(id);
        return "redirect:/mvc/doenca";
    }
}
