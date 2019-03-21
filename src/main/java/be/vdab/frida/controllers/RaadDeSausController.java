package be.vdab.frida.controllers;

import be.vdab.frida.exceptions.SpelException;
import be.vdab.frida.forms.RaadDeSausForm;
import be.vdab.frida.sessions.RaadDeSausSpel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("raaddesaus")
class RaadDeSausController {
    private RaadDeSausSpel spel;

    RaadDeSausController(RaadDeSausSpel spel) {
        this.spel = spel;
    }

    @GetMapping
    ModelAndView startPagina() {
        return new ModelAndView("raaddesaus")
                .addObject("form", new RaadDeSausForm('a'))
                .addObject("naam", spel.getVerstopteNaam());
    }

    @GetMapping("spel")
    ModelAndView pagina(RaadDeSausForm form) {
        var modelAndView = new ModelAndView("raaddesaus");
        String sausNaam = spel.verwerkBeurt(form.getLetter());
        byte beurt = spel.getAantalBeurten();
        boolean gewonnen = false;

        if (beurt > 10) {
            modelAndView.addObject("sausNaam", spel.getSausNaam());
        } else if(sausNaam.equals(spel.getSausNaam())) {
            gewonnen = true;
        }

        return modelAndView
                .addObject("form", form)
                .addObject("naam", sausNaam)
                .addObject("beurt", beurt)
                .addObject("gewonnen", gewonnen);
    }

    @GetMapping("reset")
    ModelAndView reset() {
        spel.initialiseer();
        return startPagina();
    }
}
