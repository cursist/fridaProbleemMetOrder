package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.CSVSausRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DefaultSausServiceTest {
    private SausService service;

    @Before
    public void before() {
        service = new DefaultSausService(new CSVSausRepository());
    }

    @Test
    public void findAllReturntSauzen() {
        assertTrue(
                service.findAll()
                        .stream()
                        .allMatch(saus -> saus instanceof Saus)
        );
    }

    @Test
    public void mayonaiseBevatEi() {
        Saus mayonaise = service.findAll()
                .stream()
                .filter(saus -> saus.getNaam().equals("mayonaise"))
                .findAny()
                .get();
        assertTrue(mayonaise.getIngredienten().contains("ei"));
    }

    @Test
    public void findByNaamBegintMet() {
        List<Saus> cSauzen = service.findByNaamBegintMet('c');
        assertTrue(
                cSauzen.stream()
                .map(Saus::getNaam)
                .collect(Collectors.toList())
                .contains("cocktail")
        );
        assertTrue(
                cSauzen.stream()
                        .map(Saus::getNaam)
                        .allMatch(naam -> naam.startsWith("c"))
        );
    }

    @Test
    public void sausEenIsCocktail() {
        assertEquals("cocktail",
                service.findById(1)
                        .stream()
                        .findAny()
                        .get()
                        .getNaam()
        );
    }
}