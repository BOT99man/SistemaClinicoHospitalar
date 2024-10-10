import java.util.Date;

public class Exame {
    private String id;
    private TipoExame tipo;
    private String descricao;
    private Paciente paciente;
    private Medico medico;
    private Date dataAgendamento;
    private boolean realizado;

    public Exame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo) {
        this.dataAgendamento = dataAgendamento;
        this.descricao = descricao;
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.realizado = false;
        this.tipo = tipo; //inicia como não realizado
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public TipoExame getTipo() {
        return tipo;
    }

    public void setTipo(TipoExame tipo) {
        this.tipo = tipo;
    }
}
