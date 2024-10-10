import java.util.Map;

public class SIstemaHospitalar implements SistemaClinico{
    private Map<String,Consulta> consultas;
    private Map<String,Exame> exames ;



    public void agendarConsulta(Paciente paciente, Medico medico, String data) {

    }

    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado) {

    }

    public void atualizarEstoqueRemedios(String remedio, int quantidade) {

    }

    public int consultarEstoqueRemedios(String remedio) {
        return 0;
    }

    public Consulta consultarConsulta(Paciente paciente) {
        return null;
    }

    public Exame consultarExame(Paciente paciente) {
        return null;
    }
}
