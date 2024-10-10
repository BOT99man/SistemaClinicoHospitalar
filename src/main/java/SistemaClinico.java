import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;

import java.io.IOException;

public interface SistemaClinico {
    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado);
    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException;
    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException;
    public Exame consultarExame(Paciente paciente)throws ExameNaoEncontradoException;
    public boolean atualizarExame(Exame exame)throws ExameNaoEncontradoException;
    public boolean atualizarProntuario(Prontuario prontuario)throws ProntuarioNaoEncontradoException;
    public boolean removerExame(Exame exame) throws ExameNaoEncontradoException;
    public void salvarDados() throws IOException;
    public void recuperarDados() throws IOException;
}
