package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudGrupoHabilidadeImpl;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudHabilidadeImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/grupo-habilidade")
@RequiredArgsConstructor
public class GrupoHabilidadeControllerMvc {

    private final CrudGrupoHabilidadeImpl crudGrupoHabilidade;
    private final CrudHabilidadeImpl crudHabilidadeImpl;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("grupos", crudGrupoHabilidade.findAll());
        return "domains/grupo-habilidade/list";
    }

    @GetMapping("/form")
    public String createForm(Model model) {
        model.addAttribute("grupo", new GrupoHabilidadeCreateRequestDto());
        return "domains/grupo-habilidade/form";
    }

    @PostMapping
    public String create(@ModelAttribute("grupoHabilidade") @Valid GrupoHabilidadeCreateRequestDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "domains/grupo-habilidade/form";
        }
        crudGrupoHabilidade.create(dto);
        return "redirect:/mvc/grupo-habilidade";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        GrupoHabilidadeFullResponseDto existing = crudGrupoHabilidade.findById(id);
        GrupoHabilidadeUpdateRequestDto dto = GrupoHabilidadeUpdateRequestDto.builder()
                .nome(existing.getNome())
                .build();

        model.addAttribute("id", id);
        model.addAttribute("grupo", dto);
        return "domains/grupo-habilidade/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute("grupoHabilidade") @Valid GrupoHabilidadeUpdateRequestDto dto,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "domains/grupo-habilidade/form";
        }
        crudGrupoHabilidade.update(id, dto);
        return "redirect:/mvc/grupo-habilidade";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudGrupoHabilidade.delete(id);
        return "redirect:/mvc/grupo-habilidade";
    }

    // DETALHES
    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        GrupoHabilidadeFullResponseDto grupoHabilidade = crudGrupoHabilidade.findById(id);
        List<HabilidadeFullResponseDto> habilidades = crudHabilidadeImpl.findByGroupId(id);

        model.addAttribute("grupo", grupoHabilidade);
        model.addAttribute("habilidades", habilidades);
        return "domains/grupo-habilidade/detail";
    }
}
