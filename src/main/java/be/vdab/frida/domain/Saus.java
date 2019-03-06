package be.vdab.frida.domain;

import java.util.List;

public class Saus {
    private final long nummer;
    private final String naam;
    private final List<String> ingredienten;

    public Saus(long nummer, String naam, String... ingredienten) {
        this.nummer = nummer;
        this.naam = naam;
        this.ingredienten = List.of(ingredienten);
    }

    public long getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public List<String> getIngredienten() {
        return List.copyOf(ingredienten);
    }
}
