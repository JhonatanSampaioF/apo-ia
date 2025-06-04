package fiap.kciao.apo_ia.gateways.controllers.mvc.reports;

import fiap.kciao.apo_ia.usecases.services.reports.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping
    public String relatoriosHome() {
        return "relatorios/index";
    }

    @GetMapping("/doenca/gerar")
    public String gerarDoenca(Model model) {
        model.addAttribute("relatorio", relatorioService.gerarRelatorioDoencaAbrigados());
        return "relatorios/doenca";
    }

    @GetMapping("/doenca/visualizar")
    public String visualizarDoenca(Model model) {
        model.addAttribute("relatorio", relatorioService.listarRelatoriosDoencaAbrigados());
        return "relatorios/doenca";
    }

    @GetMapping("/abrigado/gerar")
    public String gerarAbrigado(Model model) {
        model.addAttribute("relatorio", relatorioService.gerarRelatorioIdadeAbrigadosLocal());
        return "relatorios/abrigado";
    }

    @GetMapping("/abrigado/visualizar")
    public String visualizarAbrigado(Model model) {
        model.addAttribute("relatorio", relatorioService.listarRelatoriosIdadeAbrigadosLocal());
        return "relatorios/abrigado";
    }

    @GetMapping("/voluntario/gerar")
    public String gerarVoluntario(Model model) {
        model.addAttribute("relatorio", relatorioService.gerarRelatorioOcupacaoComVoluntarios());
        return "relatorios/voluntario";
    }

    @GetMapping("/voluntario/visualizar")
    public String visualizarVoluntario(Model model) {
        model.addAttribute("relatorio", relatorioService.listarRelatoriosOcupacaoComVoluntarios());
        return "relatorios/voluntario";
    }

    @GetMapping("/local/gerar")
    public String gerarLocal(Model model) {
        model.addAttribute("relatorio", relatorioService.gerarRelatorioOcupacaoLocal());
        return "relatorios/local";
    }

    @GetMapping("/local/visualizar")
    public String visualizarLocal(Model model) {
        model.addAttribute("relatorio", relatorioService.listarRelatoriosOcupacaoLocal());
        return "relatorios/local";
    }

    @GetMapping("/habilidade/gerar")
    public String gerarHabilidade(Model model) {
        model.addAttribute("relatorio", relatorioService.gerarRelatorioVoluntarioHabilidade());
        return "relatorios/habilidade";
    }

    @GetMapping("/habilidade/visualizar")
    public String visualizarHabilidade(Model model) {
        model.addAttribute("relatorio", relatorioService.listarRelatoriosVoluntarioHabilidade());
        return "relatorios/habilidade";
    }
}
