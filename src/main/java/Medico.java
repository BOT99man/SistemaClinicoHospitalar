public class Medico {
    private String nome;
    private String CRM;
    private int ID;

    public Medico(String CRM, int ID, String nome) {
        this.CRM = CRM;
        this.ID = ID;
        this.nome = nome;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
