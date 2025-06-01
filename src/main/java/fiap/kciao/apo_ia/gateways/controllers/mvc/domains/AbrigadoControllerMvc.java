package fiap.kciao.apo_ia.gateways.controllers.mvc.domains;

import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import fiap.kciao.apo_ia.gateways.mappers.domains.DoencaMapper;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudAbrigado;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mvc/abrigado")
@RequiredArgsConstructor
public class AbrigadoControllerMvc {

    private final CrudAbrigado crudAbrigado;
    private final LocalQueryService localQueryService;
    private final DoencaQueryService doencaQueryService;
    private final HabilidadeQueryService habilidadeQueryService;

    @GetMapping
    public String list(Model model) {
        List<AbrigadoFullResponseDto> abrigados = crudAbrigado.findAll();
        model.addAttribute("abrigados", abrigados);
        return "domains/abrigados/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("abrigado", new AbrigadoCreateRequestDto());
        model.addAttribute("ferimentos", List.of("Nenhum","Corte", "Fratura", "Queimadura", "Infecção", "Contusão"));
        model.addAttribute("locais", localQueryService.findAll());
        model.addAttribute("doencas", doencaQueryService.findAll());
        model.addAttribute("habilidades", habilidadeQueryService.findAll());
        model.addAttribute("capacidades", List.of("Plena", "Parcial", "Limitada", "Comprometida", "Sem mobilidade"));

        return "domains/abrigados/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        AbrigadoFullResponseDto existing = crudAbrigado.findById(id);

        AbrigadoUpdateRequestDto dto = AbrigadoUpdateRequestDto.builder()
                .nome(existing.getNome())
                .idade(existing.getIdade())
                .altura(existing.getAltura())
                .peso(existing.getPeso())
                .cpf(existing.getCpf())
                .voluntario(existing.getVoluntario())
                .ferimento(existing.getFerimento())
                .doencaIds(existing.getDoencaIds())
                .build();

        model.addAttribute("abrigado", dto);
        model.addAttribute("id", id);
        model.addAttribute("doencas", doencaQueryService.findAll());
        model.addAttribute("ferimentos", List.of("Nenhum", "Corte", "Fratura", "Queimadura", "Infecção", "Contusão"));
        model.addAttribute("locais", localQueryService.findAll());
        return "domains/abrigados/form";
    }



    @PostMapping
    public String create(@ModelAttribute("abrigado") @Valid AbrigadoCreateRequestDto dto) {
        crudAbrigado.create(dto);
        return "redirect:/mvc/abrigado";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute("abrigado") @Valid AbrigadoUpdateRequestDto dto) {
        crudAbrigado.update(id, dto);
        return "redirect:/mvc/abrigado";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        AbrigadoFullResponseDto abrigado = crudAbrigado.findById(id);

        String nomeLocal = localQueryService.findByIdOrThrow(abrigado.getLocalId()).getNome();

        List<String> nomesDoencas = Optional.ofNullable(abrigado.getDoencaIds())
                .orElse(List.of())
                .stream()
                .map(doencaQueryService::findByIdOrThrow)
                .map(DoencaMapper::toFullResponseDto)
                .map(DoencaFullResponseDto::getNome)
                .toList();


        model.addAttribute("abrigado", abrigado);
        model.addAttribute("nomeLocal", nomeLocal);
        model.addAttribute("nomesDoencas", nomesDoencas);

        return "domains/abrigados/detail";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        crudAbrigado.delete(id);
        return "redirect:/mvc/abrigado";
    }
}

