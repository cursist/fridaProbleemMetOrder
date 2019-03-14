package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSVSausRepositoryTest {
    private CSVSausRepository repository;

    @Before
    public void before() {
        repository = new CSVSausRepository();
    }

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