package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;

public class SnackForm {
    @NotBlank
    private final String beginNaam;

    public SnackForm(String beginNaam) {
        this.beginNaam = beginNaam;
    }

    public String getBeginNaam() {
        return beginNaam;
    }
}
