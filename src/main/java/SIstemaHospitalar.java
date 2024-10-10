
import Exceptions.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SistemaHospitalar implements SistemaClinico{
    private Map<String,Prontuario> prontuarios;
    private Map<String,Exame> exames ;
    private Map<String,Paciente> pacientes ;

    public SistemaHospitalar() {
        this.prontuarios = new HashMap<String,Prontuario>();
        this.exames = new HashMap<String, Exame>();
        this.pacientes = new HashMap<String, Paciente>();
    }


    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo) {
        Exame ex = new Exame(dataAgendamento, descricao, id, medico, paciente, tipo);
        this.exames.put(id, ex);
    }

    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException {
        if(this.prontuarios.containsKey(paciente.getNome())) {
            return this.prontuarios.get(paciente.getNome());
        }
        throw new ProntuarioNaoEncontradoException();
    }


    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException{
        if(this.pacientes.containsKey(nomePaciente)) {
            return this.pacientes.get(nomePaciente);
        }
        throw new PacienteNaoEncontradoException();
    }

    public Exame consultarExame(String id) throws ExameNaoEncontradoException {
        if(this.exames.containsKey(id)) {
            return this.exames.get(id);
        }
        throw new ExameNaoEncontradoException();
    }

    public boolean atualizarExame(Exame exame) throws ExameNaoEncontradoException {
        return false;
    }

    public boolean atualizarProntuario(Prontuario prontuario) throws ProntuarioNaoEncontradoException {
        return false;
    }


    public boolean removerExame(Exame exame) throws ExameNaoEncontradoException {
        return false;
    }

    public void salvarDados() throws IOException {

    }

    public void recuperarDados() throws IOException {

    }
}
