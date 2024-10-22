package SistemaHospital;

import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface SistemaClinico {
    public void adicionarPaciente(Paciente paciente)throws PacienteJaExisteException;
    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo); //ok
    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException; //fazendo
    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException; //ok
    public Exame consultarExame(String id)throws ExameNaoEncontradoException; // ok
    public boolean atualizarExame(String id)throws ExameNaoEncontradoException; // fazendo
    public boolean removerExame(String id) throws ExameNaoEncontradoException; // fazendo
    public List<Paciente> listarPacientesComExames(); //ok
    public void mostrarPacientes(); // ok
    public void salvarDados() throws IOException;
    public void recuperarDados() throws IOException;
}
