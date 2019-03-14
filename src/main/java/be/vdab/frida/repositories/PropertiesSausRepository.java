package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.RepositoryException;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Order(2)
public class PropertiesSausRepository implements SausRepository {

    @Override
    public List<Saus> findAll() {
        var pad = Paths.get("C:\\Users\\hans.dewitte\\IdeaProjects\\frida\\src\\main\\resources\\static\\sauzen.properties");
        try {
            LoggerFactory.getLogger(this.getClass()).info("gebruik gemaakt van sauzen.properties");
            return Files.lines(pad)
                    .map(regel -> sausUitString(regel))
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException ex) {
            throw new RepositoryException("fout bij het lezen van de sauzen");
        }
    }

    private Saus sausUitString(String regel) throws NumberFormatException {
        String[] csvKolommen = regel.split("[:,]");
        long nummer = Long.parseLong(csvKolommen[0]);
        String naam = csvKolommen[1];
        String[] ingredienten = Arrays.copyOfRange(csvKolommen, 2, csvKolommen.length);
        return new Saus(nummer, naam, ingredienten);
    }
}
