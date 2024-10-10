package Exceptions;

public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException() {
        super("Paciente não encontrado!");
    }
    public PacienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
