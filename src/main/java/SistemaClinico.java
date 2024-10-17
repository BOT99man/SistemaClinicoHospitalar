import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface SistemaClinico {
    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo);
    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException;
    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException;
    public Exame consultarExame(String id)throws ExameNaoEncontradoException;
    public boolean atualizarExame(String id)throws ExameNaoEncontradoException;
    public boolean removerExame(String id) throws ExameNaoEncontradoException;
    public List<Paciente> listarPacientesComExames();
    public void mostrarPacientes();
    public void salvarDados() throws IOException;
    public void recuperarDados() throws IOException;
}
