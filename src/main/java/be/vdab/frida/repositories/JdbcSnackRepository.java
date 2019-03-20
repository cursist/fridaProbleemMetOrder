package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.exceptions.RepositoryException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSnackRepository implements SnackRepository {
    private static final RowMapper<Snack> mapToSaus =
            (result, rowNum) -> new Snack(result.getLong("id"),
                                          result.getString("naam"),
                                          result.getBigDecimal("prijs"));
    private final JdbcTemplate template;

    public JdbcSnackRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Snack> findById(long id) {
        String sql = "select id, naam, prijs from snacks where id=?";
        try {
            return Optional.of(template.queryForObject(sql, mapToSaus, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Snack snack) {
        String sql = "update snacks set naam = ?, prijs = ? where id = ?";
        int aantalUpdates = template.update(sql, snack.getNaam(), snack.getPrijs(), snack.getId());
        if (aantalUpdates == 0) {
            throw new RepositoryException("geen snacks gevonden met die ID");
        }
    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {
        String sql = "select id, naam, prijs from snacks where naam like ?";
        return template.query(sql, mapToSaus, beginNaam + '%');
    }
}
