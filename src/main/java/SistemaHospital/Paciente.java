package SistemaHospital;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Paciente implements Serializable {
    private String nome;
    private Date nascimento;
    private String cpf;

    public Paciente(String nome, Date nascimento, String cpf){
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Paciente:" +
                "\nnome: " + nome +
                "\nnascimento=" + nascimento +
                "\ncpf='" + cpf + '\n' ;
    }
}
