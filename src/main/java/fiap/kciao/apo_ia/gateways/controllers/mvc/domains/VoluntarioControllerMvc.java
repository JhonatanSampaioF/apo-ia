package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudVoluntarioImpl;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudAbrigadoImpl;
import fiap.kciao.apo_ia.usecases.domains.implementations.CrudHabilidadeImpl;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc/voluntario")
@RequiredArgsConstructor
public class VoluntarioControllerMvc {

    private final CrudVoluntarioImpl crudVoluntario;
    private final CrudAbrigadoImpl crudAbrigado;
    private final CrudHabilidadeImpl crudHabilidade;
    private final AbrigadoQueryService abrigadoQueryService;
    private final HabilidadeQueryService habilidadeQueryService;

    @GetMapping
    public String list(Model model) {
        List<VoluntarioFullResponseDto> voluntarios = crudVoluntario.findAll();
        for (VoluntarioFullResponseDto voluntario : voluntarios) {
            try {
                Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(voluntario.getAbrigadoId());
                voluntario.setAbrigadoNome(abrigado.getNome());
                voluntario.setAbrigadoCpf(abrigado.getCpf());
            } catch (Exception e) {
                voluntario.setAbrigadoNome("Não encontrado");
                voluntario.setAbrigadoCpf("----");
            }
        }
        model.addAttribute("voluntarios", voluntarios);
        return "domains/voluntarios/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("voluntario", new VoluntarioCreateRequestDto());
        model.addAttribute("abrigados", crudAbrigado.findAll());
        model.addAttribute("habilidades", crudHabilidade.findAll());
        return "domains/voluntarios/form";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid VoluntarioCreateRequestDto dto) {
        crudVoluntario.create(dto);
        return "redirect:/mvc/voluntario";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        VoluntarioFullResponseDto voluntario = crudVoluntario.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("voluntario", VoluntarioUpdateRequestDto.builder()
                .capacidade_motora(voluntario.getCapacidade_motora())
                .habilidadeIds(voluntario.getHabilidadeIds())
                .build());

        model.addAttribute("capacidades", List.of("Plena", "Parcial", "Limitada", "Comprometida", "Sem mobilidade"));
        model.addAttribute("abrigados", crudAbrigado.findAll());
        model.addAttribute("habilidades", crudHabilidade.findAll());

        return "domains/voluntarios/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute @Valid VoluntarioUpdateRequestDto dto) {
        crudVoluntario.update(id, dto);
        return "redirect:/mvc/voluntario";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudVoluntario.delete(id);
        return "redirect:/mvc/voluntario";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        VoluntarioFullResponseDto voluntario = crudVoluntario.findById(id);
        model.addAttribute("voluntario", voluntario);

        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(voluntario.getAbrigadoId());
        model.addAttribute("abrigado", abrigado);

        List<Habilidade> habilidades = habilidadeQueryService.findAllById(voluntario.getHabilidadeIds());
        model.addAttribute("habilidades", habilidades);

        return "domains/voluntarios/detail";
    }
}
