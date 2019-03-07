package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/taal")
class TaalController {
    private static final String NEDERLANDS = "nl";
    @GetMapping
    ModelAndView taalPagina(@RequestHeader("Accept-Language") String acceptLanguage) {
        var modelAndView = new ModelAndView("taal",
                "isNederlands", acceptLanguage.startsWith(NEDERLANDS));
        return modelAndView;
    }

}
