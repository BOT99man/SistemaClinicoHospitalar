
import Exceptions.*;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SistemaHospitalar implements SistemaClinico {
    private Map<String, Prontuario> prontuarios;
    private Map<String, Exame> exames;
    private Map<String, Paciente> pacientes;

    public SistemaHospitalar() {
        this.prontuarios = new HashMap<String, Prontuario>();
        this.exames = new HashMap<String, Exame>();
        this.pacientes = new HashMap<String, Paciente>();
    }


    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo) {
        Exame ex = new Exame(dataAgendamento, descricao, id, medico, paciente, tipo);
        this.exames.put(id, ex);
    }

    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException {
        if (this.prontuarios.containsKey(paciente.getNome())) {
            return this.prontuarios.get(paciente.getNome());
        }
        throw new ProntuarioNaoEncontradoException();
    }


    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException {
        if (this.pacientes.containsKey(nomePaciente)) {
            return this.pacientes.get(nomePaciente);
        }
        throw new PacienteNaoEncontradoException();
    }

    public Exame consultarExame(String id) throws ExameNaoEncontradoException {
        if (this.exames.containsKey(id)) {
            return this.exames.get(id);
        }
        throw new ExameNaoEncontradoException();
    }

    public boolean atualizarExame(String id) throws ExameNaoEncontradoException {
        if (this.exames.containsKey(id)) {
            if (this.exames.get(id).isRealizado()) {
                this.exames.get(id).setRealizado(false);
                return true;
            }
            this.exames.get(id).setRealizado(true);
            return true;
        }
        throw new ExameNaoEncontradoException();
    }

    public boolean removerExame(String id) throws ExameNaoEncontradoException {
        if (this.exames.containsKey(id)) {
            this.exames.remove(id);
            return true;
        }
        throw new ExameNaoEncontradoException();
    }

    public List<Paciente> listarPacientesComExames() {
        return pacientes.values().stream().filter(paciente -> exames.values().stream().anyMatch(exame -> exame.getPaciente().equals(paciente))).collect(Collectors.toList());
    }

    public void mostrarPacientes() {
        pacientes.forEach((id, paciente) ->
                System.out.println("ID: " + id + ", Nome: " + paciente.getNome()));
    }

    public void salvarDados() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dadosSistemaClinico.dat"))) {
            oos.writeObject(this.pacientes);
            oos.writeObject(this.exames);
            oos.writeObject(this.prontuarios);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            throw new IOException("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void recuperarDados() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dadosSistemaClinico.dat"))) {
            pacientes = (HashMap<String, Paciente>) ois.readObject();
            exames = (HashMap<String, Exame>) ois.readObject();
            prontuarios = (HashMap<String, Prontuario>) ois.readObject();
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Erro ao recuperar dados: " + e.getMessage());
        }

    }
}