package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.RepositoryException;
import be.vdab.frida.repositories.SausRepository;
import be.vdab.frida.repositories.TekstBestandSausRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultSausService implements SausService {
    private final SausRepository[] repositories;

    public DefaultSausService(SausRepository[] repository) {
        this.repositories = repository;
    }

    @Override
    public List<Saus> findAll() {
        for (var repository : repositories) {
            try {
                return repository.findAll();
            } catch (RepositoryException ex) {
                LoggerFactory
                        .getLogger(this.getClass())
                        .error("fout bij het lezen: " + repository.getClass());
            }
        }
        throw new RepositoryException("kan geen repository lezen");
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {
        return this.findAll()
                .stream()
                .filter(saus -> saus.getNaam().charAt(0) == letter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Saus> findById(long id) {
        return this.findAll()
                         .stream()
                         .filter(saus -> saus.getNummer() == id)
                         .findAny();
    }
}
