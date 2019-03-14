package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import(CSVSausRepository.class)
@PropertySource("application.properties")
public class CSVSausRepositoryTest {
    @Autowired
    private SausRepository repository;

    @Test
    public void findAllReturntSauzen() {
        assertTrue(
                repository.findAll()
                          .stream()
                          .allMatch(saus -> saus instanceof Saus)
        );
    }

    @Test
    public void sausEenIsCocktail() {
        assertEquals("cocktail",
                repository.findAll()
                          .stream()
                          .filter(saus -> saus.getNummer() == 1L)
                          .findAny()
                          .get()
                          .getNaam()
        );
    }

    @Test
    public void mayonaiseBevatEi() {
        Saus maynaise = repository.findAll()
                                  .stream()
                                  .filter(saus -> saus.getNaam().equals("mayonaise"))
                                  .findAny()
                                  .get();
        assertTrue(maynaise.getIngredienten().contains("ei"));
    }
}