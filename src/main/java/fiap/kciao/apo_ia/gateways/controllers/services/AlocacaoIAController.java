package fiap.kciao.apo_ia.gateways.controllers.services;

import fiap.kciao.apo_ia.usecases.services.ai.AlocacaoIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ia")
public class AlocacaoIAController {

    private final AlocacaoIAService alocacaoIAService;

    @GetMapping("/sugestao")
    public ResponseEntity<String> obterSugestaoDeAlocacao(@RequestParam String pergunta) {
        return ResponseEntity.ok(alocacaoIAService.obterResposta(pergunta));
    }
}

