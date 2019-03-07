package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sauzen")
class SauzenController {
    @GetMapping
    ModelAndView getHtmlPagina() {
        var modelAndView = new ModelAndView("sauzen");
        modelAndView.addObject("lijstVanSauzen", getLijstVanSauzen());
        return modelAndView;
    }

    private List<Saus> getLijstVanSauzen() {
        var lijstVanSauzen = List.of(
                new Saus(1L, "cocktail", "hanestaart"),
                new Saus(2L, "mayonaise", "ei", "boter"),
                new Saus(3L, "mosterd"),
                new Saus(4L, "tartare"),
                new Saus(5L, "vinaigrette"));
        return lijstVanSauzen;
    }

    @GetMapping("{nummer}")
    ModelAndView saus(@PathVariable long nummer) {
        var deSaus = getLijstVanSauzen().stream()
                .filter(saus -> saus.getNummer() == nummer)
                .findFirst()
                .orElse(new Saus(404L, "saus not found"));
        return new ModelAndView("saus", "saus", deSaus);
    }
}
