package Exceptions;

public class ExameNaoEncontradoException extends Exception {
    public ExameNaoEncontradoException() {
        super("Exame não encontrado!");
    }

    public ExameNaoEncontradoException(String msg) {
        super(msg);
    }
}
