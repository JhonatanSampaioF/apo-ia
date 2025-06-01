package fiap.kciao.apo_ia.gateways.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRedirectController {
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}


