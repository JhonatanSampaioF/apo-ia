package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudGrupoHabilidadeImpl;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudHabilidadeImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/habilidade")
@RequiredArgsConstructor
public class HabilidadeControllerMvc {

    private final CrudHabilidadeImpl crudHabilidade;
    private final CrudGrupoHabilidadeImpl crudGrupoHabilidade;

    @GetMapping
    public String list(Model model) {
        List<HabilidadeFullResponseDto> habilidades = crudHabilidade.findAll();
        for (HabilidadeFullResponseDto habilidade : habilidades) {
            GrupoHabilidadeFullResponseDto grupoHabilidade = crudGrupoHabilidade.findById(habilidade.getGrupoHabilidadeId());
            habilidade.setGrupoHabilidadeNome(grupoHabilidade.getNome());
        }
        model.addAttribute("habilidades", habilidades);

        return "domains/habilidades/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("habilidade", new HabilidadeCreateRequestDto());
        model.addAttribute("grupos", crudGrupoHabilidade.findAll());
        return "domains/habilidades/form";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid HabilidadeCreateRequestDto dto) {
        crudHabilidade.create(dto);
        return "redirect:/mvc/habilidade";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        HabilidadeFullResponseDto habilidade = crudHabilidade.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("habilidade", HabilidadeUpdateRequestDto.builder()
                .nome(habilidade.getNome())
                .prioridade(habilidade.getPrioridade())
                .build());
        return "domains/habilidades/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute @Valid HabilidadeUpdateRequestDto dto) {
        crudHabilidade.update(id, dto);
        return "redirect:/mvc/habilidade";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudHabilidade.delete(id);
        return "redirect:/mvc/habilidade";
    }
}
