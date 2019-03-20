package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.domain.Snack;
import be.vdab.frida.forms.SnackForm;
import be.vdab.frida.services.SausService;
import be.vdab.frida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;

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

    @GetMapping("alfabet/{letter}")
    ModelAndView snacksMetLetter(@PathVariable String letter) {
        var lijstVanSnacks = service.findByBeginNaam(letter);
        return new ModelAndView("snacks")
                .addObject("lijstVanSnacks", lijstVanSnacks);
    }

    @GetMapping("opnaam/form")
    ModelAndView snacksOpNaamForm() {
        return new ModelAndView("snackForm")
                .addObject(new SnackForm("hallo"))
                .addObject("lijstVanSnacks", Collections.emptyList());
    }
    @GetMapping("opnaam")
    ModelAndView snacksOpNaam(@Valid SnackForm form, Errors errors) {
        var modelAndView = new ModelAndView("snackForm");
        if (errors.hasErrors()) {
            return modelAndView;
        } else {
            return modelAndView.addObject("lijstVanSnacks", service.findByBeginNaam(form.getBeginNaam()));
        }
    }

    @GetMapping("{id}")
    ModelAndView wijzigSnack(@PathVariable long id) {
        return new ModelAndView("wijzigen")
                .addObject(new Snack(id, null, BigDecimal.ZERO));
    }

    @PostMapping
    String toevoegen(@Valid Snack snack, Errors errors, RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            return "wijzigen";
        } else {
            service.update(snack);
            redirect.addAttribute("gewijzigd", snack.getId());
            return "redirect:/";
        }
    }
}
