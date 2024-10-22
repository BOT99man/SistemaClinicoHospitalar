package Exceptions;

public class PacienteJaExisteException extends Exception {
    public PacienteJaExisteException() {
        super("Paciente ja existe no banco de dados");
    }

    public PacienteJaExisteException(String msg) {
        super(msg);
    }
}
