package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.exceptions.RepositoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcSnackRepository.class)
public class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    JdbcSnackRepository repository;

    @Test
    public void findByIdLukt() {
        assertEquals(
                "Frikandel",
                repository.findById(1).get().getNaam()
        );
    }
    @Test
    public void findByIdMetEenOngeldigIdGeeftEenLegeOptional() {
        assertTrue(
                repository.findById(-1).isEmpty()
        );
    }

    @Test
    public void updateLukt() {
        long frikandelId = 1;
        BigDecimal nieuwePrijs = new BigDecimal("0.50");
        Snack nieuweFrikandel = new Snack(frikandelId, "Frikandel", nieuwePrijs);

        repository.update(nieuweFrikandel);
        assertEquals(
                nieuwePrijs,
                repository.findById(frikandelId)
                          .get()
                          .getPrijs()
        );
    }
    @Test(expected = RepositoryException.class)
    public void updateMetOnbestaandeIdLevertExceptionOp() {
        Snack onbestaandeSnack = new Snack(-24, "Frikandel", new BigDecimal("0.50"));
        repository.update(onbestaandeSnack);
    }

    @Test
    public void findByBeginNaam() {
        List<Snack> snacksBeginnendeMetFrik = repository.findByBeginNaam("frik");
        // de lijst bevat hetgeen wat we verwachten dat het bevat
        assertTrue(
                snacksBeginnendeMetFrik.stream()
                                       .map(Snack::getNaam)
                                       .anyMatch(naam -> naam.equals("Frikandel"))
        );
        // de lijst bevat niet meer dan wat we verwachten dat het bevat
        assertTrue(
                snacksBeginnendeMetFrik.stream()
                                       .map(Snack::getNaam)
                                       .allMatch(naam -> naam.startsWith("frik") || naam.startsWith("Frik"))
        );
    }

    @Test
    public void findByBeginNaamMetLegeStringGeeftAlleSnacks() {
        List<Snack> snacksBeginnendeMetNiks = repository.findByBeginNaam("");
        assertEquals(
                super.countRowsInTable("snacks"),
                snacksBeginnendeMetNiks.size()
        );
    }
}