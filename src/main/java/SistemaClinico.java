import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;

import java.io.IOException;
import java.util.Date;

public interface SistemaClinico {
    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo);
    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException;
    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException;
    public Exame consultarExame(String id)throws ExameNaoEncontradoException;
    public boolean atualizarExame(Exame exame)throws ExameNaoEncontradoException;
    public boolean atualizarProntuario(Prontuario prontuario)throws ProntuarioNaoEncontradoException;
    public boolean removerExame(Exame exame) throws ExameNaoEncontradoExceptio;
    public void salvarDados() throws IOException;
    public void recuperarDados() throws IOException;
}
