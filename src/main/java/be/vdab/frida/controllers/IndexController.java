package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    ModelAndView index() {
        var modelAndView = new ModelAndView("index");
        var gemeente = new Gemeente("Akker", 7000);
        var adres = new Adres("Boerenveldlaan", "3B", gemeente);
        modelAndView.addObject("adres", adres)
                    .addObject("openOfGesloten", getOpenheidOpBasisVanDag());
        return modelAndView;
    }

    private String getOpenheidOpBasisVanDag() {
        DayOfWeek dag = LocalDate.now().getDayOfWeek();
        if (dag == MONDAY || dag == THURSDAY) {
            return "gesloten";
        } else {
            return "open";
        }
    }
}
