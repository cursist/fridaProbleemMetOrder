package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.RepositoryException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Order(2)
public class PropertiesSausRepository extends TekstBestandSausRepository {
    private final String pad;
    private final String delimiters;

    public PropertiesSausRepository(@Value("${sauzenCsvUrl}") String pad) {
        this.pad = pad;
        this.delimiters = "[:,]";
    }

    @Override
    protected Path getPath() {
        return Path.of(pad);
    }

    @Override
    protected String getDelimiters() {
        return delimiters;
    }
}
