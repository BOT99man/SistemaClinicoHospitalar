
import Exceptions.*;

import java.io.IOException;
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


    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado) {

    }

    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException {
        return null;
    }


    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException{
        return null;
    }

    public Exame consultarExame(Paciente paciente) throws ExameNaoEncontradoException {
        return null;
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
