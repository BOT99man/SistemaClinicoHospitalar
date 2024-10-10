import java.util.Date;

public class Consulta {

    private Date dataDaConsulta;
    private StatusConsulta status;
    private String idDaConsulta;
    private Medico medico;
    private Paciente paciente;
    private String descricao;

    public Consulta(Date dataDaConsulta, StatusConsulta status, String idDaConsulta, Medico medico, Paciente paciente, String descricao) {
        this.dataDaConsulta = dataDaConsulta;
        this.status = status;
        this.idDaConsulta = idDaConsulta;
        this.medico = medico;
        this.paciente = paciente;
        this.descricao = descricao;
    }

    public Date getDataDaConsulta() {
        return dataDaConsulta;
    }

    public void setDataDaConsulta(Date dataDaConsulta) {
        this.dataDaConsulta = dataDaConsulta;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public String getIdDaConsulta() {
        return idDaConsulta;
    }

    public void setIdDaConsulta(String idDaConsulta) {
        this.idDaConsulta = idDaConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
