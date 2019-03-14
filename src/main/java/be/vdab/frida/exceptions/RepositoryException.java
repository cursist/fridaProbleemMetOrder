package be.vdab.frida.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String boodschap) {
        super(boodschap);
    }
}
