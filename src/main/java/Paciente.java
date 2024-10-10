import java.util.Date;
import java.util.Objects;

public class Paciente {
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
        return "Paciente:\n" +
                "nome : '" + nome + '\n' +
                "nascimento : " + nascimento +
                "\ncpf : '" + cpf + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente paciente)) return false;
        return Objects.equals(getNome(), paciente.getNome()) && Objects.equals(getNascimento(), paciente.getNascimento()) && Objects.equals(getCpf(), paciente.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getNascimento(), getCpf());
    }
}
