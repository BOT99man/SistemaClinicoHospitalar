import java.io.IOException;
import java.util.Map;

public class SistemaHospitalar implements SistemaClinico{
    private Map<String,Consulta> consultas;
    private Map<String,Exame> exames ;


    @Override
    public void agendarConsulta(Paciente paciente, Medico medico, String data, String id) {
    }

    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado) {

    }

    @Override
    public Consulta procurarConsulta(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente pesquisarPaciente(String nomePaciente) {
        return null;
    }

    public Exame consultarExame(Paciente paciente) {
        return null;
    }

    @Override
    public boolean atualizarExame(Exame exame) {
        return false;
    }

    @Override
    public boolean atualizarConsulta(Consulta consulta) {
        return false;
    }

    @Override
    public boolean removerExame(Exame exame) {
        return false;
    }

    @Override
    public void salvarDados() throws IOException {

    }

    @Override
    public void recuperarDados() throws IOException {

    }
}