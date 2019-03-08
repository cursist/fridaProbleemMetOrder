package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SauzenControllerTest {
    private SauzenController controller;

    @Before
    public void setUp() {
        controller = new SauzenController();
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
        assertTrue( lijst.get(0) instanceof Saus);
    }

    @Test
    public void sausGebruiktSausHtml() {
        assertEquals("saus", controller.saus(1).getViewName());
    }

    @Test
    public void sausGeeftSausDoor() {
        var saus = (Saus) controller.saus(1)
                                    .getModel()
                                    .get("saus");
        assertEquals(1, saus.getNummer());
    }

    @Test
    public void verkeerdeSausGeeft404Saus() {
        var saus = (Saus) controller.saus(1111)
                .getModel()
                .get("saus");
        assertEquals(404, saus.getNummer());
    }
}