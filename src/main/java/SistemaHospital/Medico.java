package SistemaHospital;

import java.io.Serializable;

public class Medico implements Serializable {
    private String nome;
    private String crm;
    private int id;

    public Medico(String crm, int id, String nome) {
        this.crm = crm;
        this.id = id;
        this.nome = nome;
    }

    public Medico(String medicoNome, String crm) {
        this.nome = medicoNome;
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
