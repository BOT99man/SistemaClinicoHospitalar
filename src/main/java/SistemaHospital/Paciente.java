package SistemaHospital;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Paciente implements Serializable {
    private String nome;
    private Date nascimento;
    private String cpf;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");


    public Paciente(String nome, String nascimento, String cpf) throws ParseException {
        this.nome = nome;
        this.nascimento = sdf1.parse(nascimento);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }

    public Paciente(String nomePaciente) {
        this.nome = nomePaciente;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cpf, paciente.cpf); // Considerando que CPF é único
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf); // Baseado no CPF, pois é único para cada paciente
    }
}



