package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {

    private static final long serialVersionUID = 1L;

    public JPanel contentPane;
    public JTextField tfChave;
    public JButton btnLimpar;
    public JButton btnSair;
    public JButton btnDecodificar;
    public JButton btnCodificar;
    public JLabel lbChave;
    public JTextArea textArea;
    public JScrollPane scroll;
    public Eventos evento = new Eventos(this);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Tela frame = new Tela();
                frame.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Tela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("Text Encoder  v310815  by Felipe.M");
        tfChave = new JTextField();
        tfChave.setBounds(24, 54, 86, 20);
        contentPane.add(tfChave);
        tfChave.setColumns(10);

        lbChave = new JLabel("Chave");
        lbChave.setBounds(24, 29, 46, 14);
        contentPane.add(lbChave);

        btnCodificar = new JButton("Codificar");
        btnCodificar.setBounds(24, 84, 89, 23);
        contentPane.add(btnCodificar);

        btnDecodificar = new JButton("Decodificar");
        btnDecodificar.setBounds(24, 118, 89, 23);
        contentPane.add(btnDecodificar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(24, 152, 89, 23);
        contentPane.add(btnLimpar);

        btnSair = new JButton("Sair");
        btnSair.setBounds(24, 186, 89, 23);

        contentPane.add(btnSair);

        JPanel panel = new JPanel();
        panel.setBounds(128, 52, 255, 177);
        contentPane.add(panel);
        panel.setLayout(null);

        textArea = new JTextArea();
        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(0, 0, 255, 177);
        scroll.setSize(255, 177);
        scroll.setLocation(0, 0);
        panel.add(scroll);

        //contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
        //	{textArea,tfChave, btnCodificar, btnDecodificar, btnLimpar, btnSair,  lbChave}));

        evento.botaoEvento();
    }

}
