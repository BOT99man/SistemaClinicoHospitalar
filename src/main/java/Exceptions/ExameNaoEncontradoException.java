package Exceptions;

public class ExameNaoEncontradoException extends Exception {
    public ExameNaoEncontradoException() {
        super("SistemaHospital.Exame não encontrado!");
    }

    public ExameNaoEncontradoException(String msg) {
        super(msg);
    }
}
