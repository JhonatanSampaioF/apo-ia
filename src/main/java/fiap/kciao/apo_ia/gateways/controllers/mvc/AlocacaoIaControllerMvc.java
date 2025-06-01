package fiap.kciao.apo_ia.gateways.controllers.mvc;

import fiap.kciao.apo_ia.usecases.services.ai.AlocacaoIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/sugestao-ia")
public class AlocacaoIaControllerMvc {

    private final AlocacaoIAService alocacaoIAService;

    @GetMapping
    public String showForm() {
        return "sugestao/form";
    }

    @PostMapping
    public String getRespostaDaIA(@RequestParam String pergunta, Model model) {
        String resposta = alocacaoIAService.obterResposta(pergunta);
        model.addAttribute("pergunta", pergunta);
        model.addAttribute("resposta", resposta);
        return "sugestao/form";
    }
}