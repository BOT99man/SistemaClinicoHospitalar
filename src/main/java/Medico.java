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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
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
