package be.vdab.frida.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository
@Order(1)
public class CSVSausRepository extends TekstBestandSausRepository {
    private final String pad;
    private final String delimiters;

    public CSVSausRepository(@Value("${sauzenCsvUrl}") String pad) {
        this.pad = pad;
        this.delimiters = ",";
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
