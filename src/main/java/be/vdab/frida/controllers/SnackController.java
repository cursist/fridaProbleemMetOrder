package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.services.SausService;
import be.vdab.frida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/snacks")
class SnackController {
    private final SnackService service;

    SnackController(SnackService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView snacks() {
        var lijstVanSnacks = service.findByBeginNaam("a");
        return new ModelAndView("snacks")
                .addObject("lijstVanSnacks", lijstVanSnacks);
    }

    @GetMapping("/{letter}")
    ModelAndView snacksMetLetter(@PathVariable String letter) {
        var lijstVanSnacks = service.findByBeginNaam(letter);
        return new ModelAndView("snacks")
                .addObject("lijstVanSnacks", lijstVanSnacks);
    }
}
