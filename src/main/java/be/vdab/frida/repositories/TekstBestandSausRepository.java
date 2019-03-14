package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.RepositoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TekstBestandSausRepository implements SausRepository {

    @Override
    public List<Saus> findAll() {
        try {
            return Files.lines(this.getPath())
                    .map(this::sausUitString)
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException ex) {
            throw new RepositoryException("fout bij het lezen van de sauzen");
        }
    }

    private Saus sausUitString(String regel) throws NumberFormatException {
        String[] csvKolommen = regel.split(this.getDelimiters());
        long nummer = Long.parseLong(csvKolommen[0]);
        String naam = csvKolommen[1];
        String[] ingredienten = Arrays.copyOfRange(csvKolommen, 2, csvKolommen.length);
        return new Saus(nummer, naam, ingredienten);
    }

    protected abstract Path getPath();
    protected abstract String getDelimiters();
}
