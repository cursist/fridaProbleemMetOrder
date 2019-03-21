package be.vdab.frida.sessions;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SpelException;
import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
@SessionScope
public class RaadDeSausSpel {
    private final SausService service;
    private String sausNaam;
    private byte aantalBeurten;
    private Set<Character> geradeLetters;

    public RaadDeSausSpel(SausService service) {
        this.service = service;
        this.initialiseer();
    }

    public void initialiseer() {
        geradeLetters = new TreeSet<>();
        aantalBeurten = 0;
        List<Saus> sauzen = service.findAll();
        int aantalSauzen = sauzen.size();
        int random = ThreadLocalRandom.current().nextInt(aantalSauzen);
        sausNaam = sauzen.get(random)
                .getNaam();
    }

    public byte getAantalBeurten() {
        return aantalBeurten;
    }

    public String getSausNaam() {
        return sausNaam;
    }

    public String verwerkBeurt(char gegokteLetter) {
        aantalBeurten++;
        geradeLetters.add(gegokteLetter);
        return getVerstopteNaam();
    }

    public String getVerstopteNaam() {
        return sausNaam.chars()
                       .map(this::charNaarPuntOfNiet)
                       .mapToObj(Character::toString)
                       .collect(Collectors.joining());
    }

    private int charNaarPuntOfNiet(int letter) {
        if (isAlGeraden(letter)) {
            return letter;
        } else {
            return '.';
        }
    }

    private boolean isAlGeraden(int letter) {
        return geradeLetters.contains((char) letter);
    }

}
