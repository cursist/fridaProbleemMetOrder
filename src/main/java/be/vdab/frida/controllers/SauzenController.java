package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sauzen")
class SauzenController {
    private final SausService service;

    SauzenController(SausService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView sauzen() {
        var modelAndView = new ModelAndView("sauzen");
        modelAndView.addObject("lijstVanSauzen", service.findAll());
        return modelAndView;
    }

    @GetMapping("{nummer}")
    ModelAndView saus(@PathVariable long nummer) {
        var deSaus = service.findById(nummer)
                .orElse(new Saus(404L, "saus not found"));
        return new ModelAndView("saus", "saus", deSaus);
    }

    @GetMapping("alfabet/{letter}")
    ModelAndView sauzenPaginaMetAlfabet(@PathVariable char letter) {
        var gefilterdeLijst = service.findByNaamBegintMet(letter);
        return new ModelAndView("alfabet",
                "lijstVanSauzen", gefilterdeLijst);
    }
}
