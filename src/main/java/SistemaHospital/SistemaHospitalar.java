package SistemaHospital;

import Exceptions.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SistemaHospitalar implements SistemaClinico {
    private Map<String, Prontuario> prontuarios;
    private Map<String, Exame> exames;
    private Map<String, Paciente> pacientes;

    public Map<String, Paciente> getPacientes() {
        return this.pacientes;
    }

    public Map<String, Exame> getExames() {
        return exames;
    }

    public SistemaHospitalar() {
        this.prontuarios = new HashMap<String, Prontuario>();
        this.exames = new HashMap<String, Exame>();
        this.pacientes = new HashMap<String, Paciente>();
    }
    public Map<String, Prontuario> getProntuarios() {
        return this.prontuarios;
    }

    @Override
    public void adicionarPaciente(Paciente paciente) throws PacienteJaExisteException {
        if (pacientes.containsKey(paciente.getNome())) {
            throw new PacienteJaExisteException();
        }
        pacientes.put(paciente.getNome(), paciente);
    }

    @Override
    public void registrarExame(Date dataAgendamento, String descricao, String id, Medico medico, Paciente paciente, TipoExame tipo) {
        Exame ex = new Exame(dataAgendamento, descricao, id, medico, paciente, tipo);
        this.exames.put(id, ex);
    }

    @Override
    public Prontuario procurarProntuario(Paciente paciente) throws ProntuarioNaoEncontradoException {
        if (this.prontuarios.containsKey(paciente.getNome())) {
            return this.prontuarios.get(paciente.getNome());
        }
        throw new ProntuarioNaoEncontradoException();
    }

    @Override
    public Paciente pesquisarPaciente(String nomePaciente) throws PacienteNaoEncontradoException {
        if (this.pacientes.containsKey(nomePaciente)) {
            return this.pacientes.get(nomePaciente);
        }
        throw new PacienteNaoEncontradoException();
    }

    @Override
    public Exame consultarExame(String id) throws ExameNaoEncontradoException {
        if (this.exames.containsKey(id)) {
            return this.exames.get(id);
        }
        throw new ExameNaoEncontradoException();
    }

    @Override
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

    @Override
    public boolean removerExame(String id) throws ExameNaoEncontradoException {
        if (this.exames.containsKey(id)) {
            this.exames.remove(id);
            return true;
        }
        throw new ExameNaoEncontradoException();
    }

    @Override
    public List<Paciente> listarPacientesComExames() {
        return pacientes.values().stream().filter(paciente -> exames.values().stream().anyMatch(exame -> exame.getPaciente().equals(paciente))).collect(Collectors.toList());
    }

    @Override
    public void mostrarPacientes() {
        pacientes.forEach((id, paciente) ->
                System.out.println("ID: " + id + ", Nome: " + paciente.getNome()));
    }

    @Override
    public void salvarDados() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("dadosSistemaClinico.txt"))) {
            for (Paciente paciente : this.pacientes.values()) {
                writer.println(paciente.getNome() + "," + paciente.getCpf() + "," + paciente.getNascimento());
            }
            writer.println("###");


            for (Exame exame : this.exames.values()) {
                writer.println(exame.getId() + "," + exame.getDescricao() + "," + exame.getDataAgendamento() + "," + exame.getMedico().getNome() + "," + exame.getMedico().getCrm() + "," + exame.getPaciente().getNome() + "," + exame.getTipo());
            }
            writer.println("###");


            for (Prontuario prontuario : this.prontuarios.values()) {
                writer.println(prontuario.getMedico() + "," + prontuario.getPaciente() + "," + prontuario.getDataRegistro() + "," + prontuario.getTratamento());
            }

            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            throw new IOException("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @Override
    public void recuperarDados() throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader("dadosSistemaClinico.txt"))) {
                String line;
                boolean readingPacientes = true; // Controla se está lendo pacientes ou exames
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato de data esperado

                while ((line = reader.readLine()) != null) {
                    // Verifica se deve mudar de leitura para exames
                    if (line.trim().equals("###")) { // Utiliza trim() para evitar espaços
                        readingPacientes = false;
                        continue;
                    }

                    // Processa os dados dos pacientes
                    if (readingPacientes) {
                        String[] dadosPaciente = line.split(","); // Divide a linha pelos vírgulas
                        if (dadosPaciente.length >= 1) { // Verifica se há dados suficientes
                            try {
                                Paciente paciente = new Paciente(dadosPaciente[0]); // Ajuste conforme sua classe Paciente
                                this.pacientes.put(paciente.getNome(), paciente);
                            } catch (Exception e) {
                                System.err.println("Erro ao criar Paciente: " + e.getMessage() + " | Dados: " + line);
                            }
                        } else {
                            System.err.println("Dados insuficientes para criar Paciente: " + line);
                        }
                    } else { // Processa os dados dos exames
                        String[] dadosExame = line.split(",");
                        if (dadosExame.length >= 6) { // Verifica se há dados suficientes
                            try {
                                Medico medico = new Medico(dadosExame[3], dadosExame[4]); // Ajuste conforme sua classe Medico
                                Paciente paciente = this.pacientes.get(dadosExame[4]); // Obtém o paciente usando o nome
                                if (paciente != null) {
                                    // Converte a data de forma segura
                                    Date dataAgendamento = null;
                                    try {
                                        dataAgendamento = sdf.parse(dadosExame[2]);
                                    } catch (ParseException e) {
                                        System.err.println("Data inválida para o exame: " + dadosExame[2]);
                                    }

                                    // Cria o exame
                                    if (dataAgendamento != null) { // Apenas cria se a data for válida
                                        Exame exame = new Exame(dataAgendamento, dadosExame[1], dadosExame[0], medico, paciente, TipoExame.valueOf(dadosExame[5])); // Ajuste conforme sua classe Exame
                                        this.exames.put(exame.getId(), exame);
                                    }
                                } else {
                                    System.err.println("Paciente não encontrado para o exame: " + dadosExame[4]);
                                }
                            } catch (Exception e) {
                                System.err.println("Erro ao criar Exame: " + e.getMessage() + " | Dados: " + line);
                            }
                        } else {
                            System.err.println("Dados insuficientes para criar Exame: " + line);
                        }
                    }
                }
                System.out.println("Dados recuperados com sucesso!");
            } catch (IOException e) {
                throw new IOException("Erro ao recuperar dados: " + e.getMessage());
            }
    }

}
