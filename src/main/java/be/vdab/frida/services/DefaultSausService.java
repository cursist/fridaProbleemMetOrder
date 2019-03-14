package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.SausRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultSausService implements SausService {
    private final SausRepository repository;

    public DefaultSausService(SausRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Saus> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {
        String eersteLetter = String.valueOf(letter);
        return this.findAll()
                .stream()
                .filter(saus -> saus.getNaam().startsWith(eersteLetter))
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
