package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RaadDeSausForm {
    private final char letter;

    public RaadDeSausForm(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
