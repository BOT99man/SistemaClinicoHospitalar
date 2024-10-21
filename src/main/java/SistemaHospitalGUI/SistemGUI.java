package SistemaHospitalGUI;

import javax.swing.*;
import java.awt.*;

public class SistemGUI extends JFrame {

    JLabel linha1, linha2;
    ImageIcon logo = new ImageIcon("C:/Users/Silva/OneDrive/Imagens/Capturas de tela/a.png/");
    public SistemGUI(){
        setTitle("Sistema Hospitalar");
        setSize(777, 666);
        setLocation(150, 150);
        setResizable(true);
        getContentPane().setBackground(Color.GRAY);
        linha1 = new JLabel("Sistema Hospitalar HealthConnect", JLabel.CENTER);
        linha1.setForeground(Color.black);
        linha1.setFont(new Font("Calibre", Font.BOLD, 30));

        linha2 = new JLabel(logo, JLabel.CENTER);

        getContentPane().setLayout(new GridLayout(3, 1));
        getContentPane().add(linha1);
        getContentPane().add(linha2);

        JButton botaoEntrar;


    }

}
