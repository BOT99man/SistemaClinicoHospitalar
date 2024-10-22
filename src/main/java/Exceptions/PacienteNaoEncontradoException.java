package Exceptions;

public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException() {
        super("SistemaHospital.Paciente não encontrado!");
    }
    public PacienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
