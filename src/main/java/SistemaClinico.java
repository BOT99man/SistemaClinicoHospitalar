import java.io.IOException;

public interface SistemaClinico {
    public void agendarConsulta(Paciente paciente, Medico medico, String data);
    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado);
    public Consulta procurarConsulta(Paciente paciente);
    public Paciente pesquisarPaciente(String nomePaciente);
    public Exame consultarExame(Paciente paciente);
    public boolean atualizarExame(Exame exame);
    public boolean atualizarConsulta(Consulta consulta);
    public boolean removerExame(Exame exame);
    public void salvarDados() throws IOException;
    public void recuperarDados() throws IOException;
}
