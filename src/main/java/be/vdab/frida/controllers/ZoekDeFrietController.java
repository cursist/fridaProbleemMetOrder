package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zoekdefriet")
class ZoekDeFrietController {
    @GetMapping
    String pagina() {
        return "zoekdefriet";
    }
}
