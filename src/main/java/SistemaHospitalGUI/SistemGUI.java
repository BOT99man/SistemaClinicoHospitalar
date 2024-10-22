package SistemaHospitalGUI;

import SistemaHospital.*;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SistemGUI extends JFrame {
    private SistemaHospitalar sistemaHospitalar;

    // Componentes da interface
    private JTextField campoNomePaciente, campoIdExame, campoDescricaoExame;
    private JTextArea areaExibicao;
    private JButton botaoCadastrarPaciente, botaoCadastrarExame, botaoListarPacientes, botaoConsultarExame, botaoProcurarProntuario, botaoPesquisarPaciente, botaoAtualizarExame, botaoRemoverExame;
    private JLabel logoLabel;

    public SistemGUI(SistemaHospitalar sistemaHospitalar) {
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

        botaoProcurarProntuario = new JButton("Procurar Prontuario");
        botaoPesquisarPaciente = new JButton("Pesquisar Paciente");
        botaoAtualizarExame = new JButton("Atualizar Exame");
        botaoRemoverExame = new JButton("remover Exame");


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

        //test
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


        // Adicionando os painéis na janela principal
        add(painelEntrada, BorderLayout.NORTH);

        // Configuração da área de exibição com JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(areaExibicao, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionando o logotipo na parte inferior
        add(logoLabel, BorderLayout.SOUTH); // Coloca a imagem no sul (embaixo)
    }

    private void configurarEventos() {
        // Ação para cadastrar paciente
        botaoCadastrarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                System.out.println("iniciando cadastrar paciente");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(0, 2)); // Usar um layout de grade

                // Campos de entrada
                JTextField nomeField = new JTextField();
                JTextField dataField = new JTextField();
                JTextField cpfField = new JTextField();

                // Adicionando os campos ao painel
                panel.add(new JLabel("Nome:"));
                panel.add(nomeField);
                panel.add(new JLabel("Data de Nascimento: dd/MM/yyyy"));
                panel.add(dataField);
                panel.add(new JLabel("CPF:"));
                panel.add(cpfField);

                // Exibir o painel em um JOptionPane
                int resultado = JOptionPane.showConfirmDialog(null, panel,
                        "Inserir Informações", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Verificar se o usuário pressionou OK
                if (resultado == JOptionPane.OK_OPTION) {
                    // Obter as informações dos campos


                    String nome = nomeField.getText();
                    Date dataNascimento = null;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Define o formato esperado
                    try {
                        dataNascimento = formatter.parse(dataField.getText());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (dataNascimento != null) {
                        // Exemplo de uso da data convertida
                        System.out.println("A data convertida é: " + dataNascimento);
                    } else {
                        System.out.println("A data não foi convertida com sucesso.");
                    }
                    String cpf = cpfField.getText();

                    // Exibir as informações inseridas
                    JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nIdade: " + dataNascimento + "\nEmail: " + cpf);

                    Paciente paciente = new Paciente(nome, dataNascimento, cpf);

                    //TODO: adiconar o paciente na lista

                    JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!");
                } else {
                    System.out.println("Operação cancelada.");
                }
            }
        }); // adicionar metodos

        // Ação para cadastrar exame
        botaoCadastrarExame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando cadastrar exame");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(0, 2)); // Usar um layout de grade

                // Campos de entrada
                JTextField idField = new JTextField();
                JTextField descricaoField = new JTextField();
                JTextField dataField = new JTextField();
                JTextField pacienteField = new JTextField();
                JTextField medicoField = new JTextField();

                // Adicionando os campos ao painel
                panel.add(new JLabel("id:"));
                panel.add(idField);
                panel.add(new JLabel("descrição:"));
                panel.add(descricaoField);
                panel.add(new JLabel("Data Agendamento dd/MM/yyyy:"));
                panel.add(dataField);
                panel.add(new JLabel("Nome Paciente:"));
                panel.add(pacienteField);
                panel.add(new JLabel("Nome do Medico:"));
                panel.add(medicoField);


                // Exibir o painel em um JOptionPane
                int resultado = JOptionPane.showConfirmDialog(null, panel,
                        "Inserir Informações", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Verificar se o usuário pressionou OK
                if (resultado == JOptionPane.OK_OPTION) {
                    // Obter as informações dos campos
                    String id = idField.getText();
                    String descricao= descricaoField.getText();
                    Date data = null;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Define o formato esperado
                    try {
                        data = formatter.parse(dataField.getText());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (data != null) {
                        // Exemplo de uso da data convertida
                        System.out.println("A data convertida é: " + data);
                    } else {
                        System.out.println("A data não foi convertida com sucesso.");
                    }
                    String paciente= pacienteField.getText();
                    String medico = medicoField.getText();

                    // Exibir as informações inseridas
                    JOptionPane.showMessageDialog(null, "id: " + id + "\ndescrição: " + descricao + "\ndata: " + data + "\nPaciente:" + paciente + "\nmedico:" + medico);

                    //TODO: cadastrar exame

                    JOptionPane.showMessageDialog(null,"Cadastro de exame concluido ");


                } else {
                    System.out.println("Operação cancelada.");
                }
            }
        }); // adicionar metodos

        // Ação para listar pacientes
        botaoListarPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando listar pacientes");
                // Criação do JList com os nomes

                //TODO: ve se os bagulho de criar o arquibo e ler ta certo que dps e so arrumar uma maneira de coloca essa lista de nomes aqui a baixp

                String[] nomes = {"Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda"};

/*
                ArrayList nomes = new ArrayList<>();
                for(String x: sistemaHospitalar.getPacientes().keySet()){
                    nomes.add(sistemaHospitalar.getPacientes().get(x));
                }
*/

                JList<String> listaNomes = new JList<>(nomes);
                listaNomes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de seleção única

                // Coloca o JList em um JScrollPane para permitir rolagem, se necessário
                JScrollPane scrollPane = new JScrollPane(listaNomes);

                // Exibe a janela de diálogo com a lista de nomes
                int result = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Selecione um Paciente",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Verifica se o usuário clicou em OK
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o nome selecionado
                    String nomeSelecionado = listaNomes.getSelectedValue();

                    if (nomeSelecionado != null) {
                        // Exibe o nome selecionado em outro JOptionPane
                        JOptionPane.showMessageDialog(null, "Você selecionou: " + nomeSelecionado);
                    } else {
                        // Caso nenhum nome tenha sido selecionado
                        JOptionPane.showMessageDialog(null, "Nenhum nome foi selecionado.");
                    }
                } else {
                    // Caso o usuário cancele a operação
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }
            }
        }); // ainda n ta pronto

        // Ação para consultar exame
        botaoConsultarExame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando consultar exame");

                String[] exames = {"exame01", "exame02", "exame03", "exame04"};

                /*ArrayList nomes = new ArrayList<>();
                for(String x: sistemaHospitalar.getExames().keySet()){
                    nomes.add(sistemaHospitalar.getExames().get(x));
                }*/

                JList<String> listaNomes = new JList<>(exames);
                listaNomes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de seleção única

                // Coloca o JList em um JScrollPane para permitir rolagem, se necessário
                JScrollPane scrollPane = new JScrollPane(listaNomes);

                // Exibe a janela de diálogo com a lista de nomes
                int result = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Selecione o exame",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Verifica se o usuário clicou em OK
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o nome selecionado
                    String nomeSelecionado = listaNomes.getSelectedValue();

                    if (nomeSelecionado != null) {
                        // Exibe o nome selecionado em outro JOptionPane
                        JOptionPane.showMessageDialog(null, "Você selecionou: " + nomeSelecionado);
                    } else {
                        // Caso nenhum nome tenha sido selecionado
                        JOptionPane.showMessageDialog(null, "Nenhum nome foi selecionado.");
                    }
                } else {
                    // Caso o usuário cancele a operação
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }
            }
        });

        botaoProcurarProntuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando Procurar Prontuario");

                String[] exames = {"prontuario01", "prontuario02", "prontuario03", "prontuario04"};

//TODO: selicionar o exame e deposi mostrar as informações, tem que fazer mais coisa dps de tu conseguir colocar os dados dos exames aq

                /*ArrayList nomes = new ArrayList<>();
                for(String x: sistemaHospitalar.getExames().keySet()){
                    nomes.add(sistemaHospitalar.getExames().get(x));
                }*/

                JList<String> listaNomes = new JList<>(exames);
                listaNomes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de seleção única

                // Coloca o JList em um JScrollPane para permitir rolagem, se necessário
                JScrollPane scrollPane = new JScrollPane(listaNomes);

                // Exibe a janela de diálogo com a lista de nomes
                int result = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Selecione o exame",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Verifica se o usuário clicou em OK
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o nome selecionado
                    String nomeSelecionado = listaNomes.getSelectedValue();

                    if (nomeSelecionado != null) {
                        // Exibe o nome selecionado em outro JOptionPane
                        JOptionPane.showMessageDialog(null, "Você selecionou: " + nomeSelecionado);
                    } else {
                        // Caso nenhum nome tenha sido selecionado
                        JOptionPane.showMessageDialog(null, "Nenhum nome foi selecionado.");
                    }
                } else {
                    // Caso o usuário cancele a operação
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }
            }
        });

        botaoPesquisarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando Pesquisar Paciente");

                String nomePaciente = JOptionPane.showInputDialog(null, "Digite o nome do paciente:", "Nome do Paciente", JOptionPane.QUESTION_MESSAGE);

                try {

                    JOptionPane.showMessageDialog(null, sistemaHospitalar.pesquisarPaciente(nomePaciente));

                } catch (PacienteNaoEncontradoException ex) {
                    //tem algo dando erro dps ve
                    JOptionPane.showMessageDialog(null,"Paciente Não encontrado");
                    throw new RuntimeException(ex);
                }
            }
        });

        botaoAtualizarExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando Atualizar Exame");

                //selecionar o exame e dps ele vai atualizar oque for preciso

                System.out.println("iniciando consultar exame");

                String[] exames = {"exame01", "exame02", "exame03", "exame04"};

                /*ArrayList nomes = new ArrayList<>();
                for(String x: sistemaHospitalar.getExames().keySet()){
                    nomes.add(sistemaHospitalar.getExames().get(x));
                }*/

                JList<String> listaNomes = new JList<>(exames);
                listaNomes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de seleção única

                // Coloca o JList em um JScrollPane para permitir rolagem, se necessário
                JScrollPane scrollPane = new JScrollPane(listaNomes);

                // Exibe a janela de diálogo com a lista de nomes
                int result = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Selecione o exame",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Verifica se o usuário clicou em OK
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o nome selecionado
                    String nomeSelecionado = listaNomes.getSelectedValue();

                    if (nomeSelecionado != null) {
                        // Exibe o nome selecionado em outro JOptionPane
                        JOptionPane.showMessageDialog(null, "Você selecionou: " + nomeSelecionado);
                    } else {
                        // Caso nenhum nome tenha sido selecionado
                        JOptionPane.showMessageDialog(null, "Nenhum nome foi selecionado.");
                    }
                } else {
                    // Caso o usuário cancele a operação
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }

            }
        });

        botaoRemoverExame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("iniciando Remover exame");

                //todo: selicionar o exame e remover

                System.out.println("iniciando consultar exame");

                String[] exames = {"exame01", "exame02", "exame03", "exame04"};

                /*ArrayList nomes = new ArrayList<>();
                for(String x: sistemaHospitalar.getExames().keySet()){
                    nomes.add(sistemaHospitalar.getExames().get(x));
                }*/

                JList<String> listaNomes = new JList<>(exames);
                listaNomes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de seleção única

                // Coloca o JList em um JScrollPane para permitir rolagem, se necessário
                JScrollPane scrollPane = new JScrollPane(listaNomes);

                // Exibe a janela de diálogo com a lista de nomes
                int result = JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,
                        "Selecione o exame",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Verifica se o usuário clicou em OK
                if (result == JOptionPane.OK_OPTION) {
                    // Obtém o nome selecionado
                    String nomeSelecionado = listaNomes.getSelectedValue();

                    if (nomeSelecionado != null) {
                        // Exibe o nome selecionado em outro JOptionPane
                        JOptionPane.showMessageDialog(null, "Você selecionou: " + nomeSelecionado);
                    } else {
                        // Caso nenhum nome tenha sido selecionado
                        JOptionPane.showMessageDialog(null, "Nenhum nome foi selecionado.");
                    }
                } else {
                    // Caso o usuário cancele a operação
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SistemaHospitalar sistema = new SistemaHospitalar();
        SistemGUI gui = new SistemGUI(sistema);
        gui.setVisible(true);
    }
}