package Exceptions;

public class ProntuarioNaoEncontradoException extends Exception{
    public ProntuarioNaoEncontradoException(){
        super("SistemaHospital.Prontuario não encontrado!");
    }

    public ProntuarioNaoEncontradoException(String msg){
        super(msg);
    }
}
