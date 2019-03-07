package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    ModelAndView index(HttpServletResponse response,
                       @CookieValue(name = "terugkerendBezoeker", required = false) String cookie) {
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("adres", getAdres())
                    .addObject("openOfGesloten", getOpenheidOpBasisVanDag());
        if (cookie == null) {
            var newCookie = new Cookie("terugkerendBezoeker", "true");
            newCookie.setMaxAge(31_536_000);
            response.addCookie(newCookie);
        } else {
            modelAndView.addObject("terugkerendBezoeker", true);
        }
        return modelAndView;
    }

    private Adres getAdres() {
        var gemeente = new Gemeente("Akker", 7000);
        return new Adres("Boerenveldlaan", "3B", gemeente);
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
