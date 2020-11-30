package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JanelaXML extends JFrame {

    private static final long serialVersionUID = 2995451402669481650L;

    private JPanel panel;
    private JLabel label;
    private JButton buttonOK;
    private JButton buttonFechar;

    public JanelaXML() {
        super("Janela XML");
        criarComponentes();
        configurarJanela();
    }

    private void criarComponentes() {
        panel = new JPanel();
        label = new JLabel();

        buttonOK = new JButton("Exportar");
        buttonOK.addActionListener(event -> exportarArtigos());

        buttonFechar = new JButton("Fechar");
        buttonFechar.addActionListener(event -> fechar());

        panel.add(buttonOK);
        panel.add(buttonFechar);
        panel.add(label);
        add(panel);
    }

    private void configurarJanela() {
        setVisible(true);
        setSize(400, 100);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void exportarArtigos() {
        label.setText("Exportando...");
        buttonOK.setEnabled(false);
        TarefaBackground tarefa = new TarefaBackground(label, buttonOK, "artigos.xml");
        tarefa.execute();
    }

    private void fechar() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JanelaXML::new); //executamos a janela na thread do Swing (Event Dispatch)
    }
}
