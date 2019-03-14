package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.CSVSausRepository;
import be.vdab.frida.repositories.PropertiesSausRepository;
import be.vdab.frida.repositories.SausRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import(CSVSausRepository.class)
@PropertySource("application.properties")
public class DefaultSausServiceTest {
    private SausService service;
    @Autowired
    private SausRepository repository;

    @Before
    public void before() {
        service = new DefaultSausService(new SausRepository[] {repository});
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