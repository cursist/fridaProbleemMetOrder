package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.services.SausService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SauzenControllerTest {
    private SauzenController controller;
    @Mock private SausService service;
    private final static List<Saus> lijstVanSauzen = List.of(
            new Saus(1L, "cocktail", "hanestaart"),
            new Saus(2L, "mayonaise", "ei", "boter"),
            new Saus(3L, "mosterd"),
            new Saus(4L, "tartare"),
            new Saus(5L, "vinaigrette"));

    @Before
    public void setUp() {
        when(service.findAll()).thenReturn(lijstVanSauzen);
        when(service.findById(1L)).thenReturn(Optional.of(lijstVanSauzen.get(0)));
        controller = new SauzenController(service);
    }

    @Test
    public void sauzenGebruiktSauzenHtml() {
        assertEquals("sauzen", controller.sauzen().getViewName());
    }

    @Test
    public void sauzenGeeftSauzenDoor() {
        Object mogelijkeLijst = controller.sauzen()
                                          .getModel()
                                          .get("lijstVanSauzen");
        assertTrue(mogelijkeLijst instanceof List);

        List lijst = (List) mogelijkeLijst;
        assertTrue( lijst.stream().findAny().get() instanceof Saus);
    }

    @Test
    public void sausGebruiktSausHtml() {
        assertEquals("saus", controller.saus(1).getViewName());
    }

    @Test
    public void sausGeeftSausDoor() {
        var saus = (Saus) controller.saus(1L)
                                    .getModel()
                                    .get("saus");
        assertEquals(1L, saus.getNummer());
    }

    @Test
    public void verkeerdeSausGeeft404Saus() {
        var saus = (Saus) controller.saus(1111)
                .getModel()
                .get("saus");
        assertEquals(404, saus.getNummer());
    }
}