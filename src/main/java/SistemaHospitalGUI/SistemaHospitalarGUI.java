package SistemaHospitalGUI;

import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;
import SistemaHospital.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SistemaHospitalarGUI extends JFrame {
    private SistemaHospitalar sistemaHospitalar;

    // Componentes da interface
    private JTextArea areaExibicao;
    private JButton botaoCadastrarPaciente, botaoCadastrarExame, botaoListarPacientes, botaoConsultarExame;
    private JButton botaoProcurarProntuario, botaoPesquisarPaciente, botaoAtualizarExame, botaoRemoverExame;
    private JButton botaoSalvarDados, botaoConsultarDados, botaoMostrarPacientes;
    private JLabel logoLabel;

    public SistemaHospitalarGUI(SistemaHospitalar sistemaHospitalar) {
        this.sistemaHospitalar = sistemaHospitalar;
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setTitle("Sistema Hospitalar");
        setSize(600, 500);
        setLocationRelativeTo(null);  // Centralizar a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        // Criando os componentes da interface
        areaExibicao = new JTextArea(10, 40);
        areaExibicao.setEditable(false);
        areaExibicao.setLineWrap(true); // Permitir quebra de linha
        areaExibicao.setWrapStyleWord(true); // Quebrar linhas por palavras

        botaoCadastrarPaciente = new JButton("Cadastrar Paciente");
        botaoCadastrarExame = new JButton("Cadastrar Exame");
        botaoListarPacientes = new JButton("Listar Pacientes");
        botaoConsultarExame = new JButton("Consultar Exame");
        botaoProcurarProntuario = new JButton("Procurar Prontuário");
        botaoPesquisarPaciente = new JButton("Pesquisar Paciente");
        botaoAtualizarExame = new JButton("Atualizar Exame");
        botaoRemoverExame = new JButton("Remover Exame");
        botaoSalvarDados = new JButton("Salvar Dados");
        botaoConsultarDados = new JButton("Consultar Dados");
        botaoMostrarPacientes = new JButton("Mostrar Pacientes");

        // Carregar a imagem do logotipo (logoHospital.png)
        ImageIcon logoIcon = new ImageIcon("src/main/java/logoHospital.jpg");
        logoLabel = new JLabel(logoIcon); // Adicionar imagem ao JLabel
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Centralizar a imagem no painel
    }

    private void configurarLayout() {
        // Usando GridBagLayout para melhor controle responsivo
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento interno

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelEntrada.add(botaoCadastrarPaciente, gbc);

        gbc.gridx = 1;
        painelEntrada.add(botaoCadastrarExame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painelEntrada.add(botaoListarPacientes, gbc);

        gbc.gridx = 1;
        painelEntrada.add(botaoConsultarExame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        painelEntrada.add(botaoAtualizarExame, gbc);

        gbc.gridx = 1;
        painelEntrada.add(botaoRemoverExame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        painelEntrada.add(botaoProcurarProntuario, gbc);

        gbc.gridx = 1;
        painelEntrada.add(botaoPesquisarPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        painelEntrada.add(botaoSalvarDados, gbc);

        gbc.gridx = 1;
        painelEntrada.add(botaoConsultarDados, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        painelEntrada.add(botaoMostrarPacientes, gbc);

        // Adicionando os painéis na janela principal
        add(painelEntrada, BorderLayout.NORTH);

        // Configuração da área de exibição com JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(areaExibicao, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionando o logotipo na parte inferior
        add(logoLabel, BorderLayout.SOUTH); // Coloca a imagem no sul (embaixo)
    }

    private void configurarEventos() {
        // Evento para Cadastrar Paciente
        botaoCadastrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
                String nascimento = JOptionPane.showInputDialog("Digite a data de nascimento do paciente(dd/MM/yyyy):");
                String cpf = JOptionPane.showInputDialog("Digite o cpf do paciente:");

                try {
                    Paciente paciente = new Paciente(nomePaciente, nascimento, cpf);
                    sistemaHospitalar.adicionarPaciente(paciente);
                    areaExibicao.append("Paciente " + nomePaciente + " cadastrado com sucesso.\n");
                } catch (PacienteJaExisteException ex) {
                    areaExibicao.append("Erro: Paciente já existe.\n");
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Evento para Cadastrar Exame
        botaoCadastrarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idExame = JOptionPane.showInputDialog("Digite o ID do exame:");
                // Simplesmente um exemplo de registro, expandir conforme a lógica de negócios
                areaExibicao.append("Exame " + idExame + " cadastrado com sucesso.\n");
            }
        });

        // Evento para Listar Pacientes
        botaoListarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Paciente> pacientes = sistemaHospitalar.listarPacientesComExames();
                areaExibicao.setText("Pacientes com exames:\n");
                for (Paciente paciente : pacientes) {
                    areaExibicao.append(paciente.getNome() + "\n");
                }
            }
        });

        // Evento para Consultar Exame
        botaoConsultarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idExame = JOptionPane.showInputDialog("Digite o ID do exame:");
                try {
                    Exame exame = sistemaHospitalar.consultarExame(idExame);
                    areaExibicao.append("Exame encontrado: " + exame.getDescricao() + "\n");
                } catch (ExameNaoEncontradoException ex) {
                    areaExibicao.append("Erro: Exame não encontrado.\n");
                }
            }
        });

        // Evento para Atualizar Exame
        botaoAtualizarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idExame = JOptionPane.showInputDialog("Digite o ID do exame para atualizar:");
                try {
                    boolean atualizado = sistemaHospitalar.atualizarExame(idExame);
                    if (atualizado) {
                        areaExibicao.append("Exame atualizado com sucesso.\n");
                    }
                } catch (ExameNaoEncontradoException ex) {
                    areaExibicao.append("Erro: Exame não encontrado.\n");
                }
            }
        });

        // Evento para Remover Exame
        botaoRemoverExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idExame = JOptionPane.showInputDialog("Digite o ID do exame para remover:");
                try {
                    boolean removido = sistemaHospitalar.removerExame(idExame);
                    if (removido) {
                        areaExibicao.append("Exame removido com sucesso.\n");
                    }
                } catch (ExameNaoEncontradoException ex) {
                    areaExibicao.append("Erro: Exame não encontrado.\n");
                }
            }
        });

        // Evento para Procurar Prontuário
        botaoProcurarProntuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
                try {
                    Prontuario prontuario = sistemaHospitalar.procurarProntuario(new Paciente(nomePaciente));
                    areaExibicao.append("Prontuário encontrado para o paciente: " + nomePaciente + "\n");
                } catch (ProntuarioNaoEncontradoException ex) {
                    areaExibicao.append("Erro: Prontuário não encontrado.\n");
                }
            }
        });

        // Evento para Pesquisar Paciente
        botaoPesquisarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente para pesquisar:");
                try {
                    Paciente paciente = sistemaHospitalar.pesquisarPaciente(nomePaciente);
                    areaExibicao.append("Paciente encontrado: " + paciente.getNome() + "\n");
                } catch (PacienteNaoEncontradoException ex) {
                    areaExibicao.append("Erro: Paciente não encontrado.\n");
                }
            }
        });
        botaoSalvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sistemaHospitalar.salvarDados();
                    JOptionPane.showMessageDialog(SistemaHospitalarGUI.this, "Dados salvos com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SistemaHospitalarGUI.this, "Erro ao salvar dados: " + ex.getMessage());
                }
            }
        });
        botaoConsultarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sistemaHospitalar.recuperarDados();
                    JOptionPane.showMessageDialog(SistemaHospitalarGUI.this, "Dados recuperados com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SistemaHospitalarGUI.this, "Erro ao recuperar dados: " + ex.getMessage());
                }
            }
        });

        botaoMostrarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistemaHospitalar.mostrarPacientes();
            }
        });
    }

    public static class main {
        public static void main(String[] args) {
            // Criar uma instância do sistema hospitalar
            SistemaHospitalar sistemaHospitalar = new SistemaHospitalar();

            // Criar e exibir a GUI
            SwingUtilities.invokeLater(() -> {
                SistemaHospitalarGUI gui = new SistemaHospitalarGUI(sistemaHospitalar);
                gui.setVisible(true);  // Tornar a janela visível
            });
        }
    }
}
