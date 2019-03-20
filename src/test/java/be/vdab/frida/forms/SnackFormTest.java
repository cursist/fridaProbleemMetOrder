package be.vdab.frida.forms;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.*;

public class SnackFormTest {
    private Validator validator;
    @Before
    public void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void stringOk() {
        assertTrue(
                validator.validateValue(
                        SnackForm.class, "beginNaam", "frik"
                ).isEmpty()
        );
    }

    @Test
    public void nullIsNietOk() {
        assertFalse(
                validator.validateValue(
                        SnackForm.class, "beginNaam", null
                ).isEmpty()
        );
    }

    @Test
    public void eenLegeStringIsNietOk() {
        assertFalse(
                validator.validateValue(
                        SnackForm.class, "beginNaam", ""
                ).isEmpty()
        );
    }
    @Test
    public void enkelSpatiesZijnNietOk() {
        assertFalse(
                validator.validateValue(
                        SnackForm.class, "beginNaam", "  "
                ).isEmpty()
        );
    }

}