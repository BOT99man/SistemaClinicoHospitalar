package Exceptions;

public class ProntuarioNaoEncontradoException extends Exception{
    public ProntuarioNaoEncontradoException(){
        super("Prontuario não encontrado!");
    }

    public ProntuarioNaoEncontradoException(String msg){
        super(msg);
    }
}
