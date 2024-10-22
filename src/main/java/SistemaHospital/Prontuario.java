package SistemaHospital;

import java.io.Serializable;
import java.util.Date;

public class Prontuario implements Serializable {
    private Date dataRegistro;
    private Medico medico;
    private Paciente paciente;
    private String tratamento;

    public Prontuario(Date dataRegistro, Medico medico, Paciente paciente, String tratamento) {
        this.dataRegistro = dataRegistro;
        this.medico = medico;
        this.paciente = paciente;
        this.tratamento = tratamento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }
}
