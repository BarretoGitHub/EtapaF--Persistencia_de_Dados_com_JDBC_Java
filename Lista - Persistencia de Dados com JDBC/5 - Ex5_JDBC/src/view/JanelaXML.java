package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entity.Instituicao;

public class JanelaXML extends JFrame {

    private static final long serialVersionUID = 2995451402669481650L;

    private JPanel panel1;
    private JPanel panel2;
    private JLabel labelLer;
    private JButton buttonLer;
    private JButton buttonSalvar;
    private JButton buttonFechar;
    private JProgressBar progressBar;
    private JTextArea area;
    private JScrollPane scroll;

    private Instituicao instituicao;

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public JanelaXML() {
        super("Janela XML");
        criarComponentes();
        configurarJanela();
    }

    private void criarComponentes() {
        setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel2 = new JPanel();
        labelLer = new JLabel();

        buttonLer = new JButton("Importar");
        buttonLer.addActionListener(event -> lerXML());

        buttonSalvar = new JButton("Salvar");
        buttonSalvar.setEnabled(false);
        buttonSalvar.addActionListener(event -> salvarNoBD());

        buttonFechar = new JButton("Fechar");
        buttonFechar.addActionListener(event -> fechar());

        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        area = new JTextArea(10, 10);
        scroll = new JScrollPane(area);

        panel1.add(buttonLer);
        panel1.add(labelLer);
        panel1.add(buttonSalvar);
        panel1.add(progressBar);
        panel1.add(buttonFechar);
        panel2.add(scroll);
        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.CENTER);
    }

    private void configurarJanela() {
        setVisible(true);
        //pack();
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void lerXML() {
        LeituraXMLBackground tarefa = new LeituraXMLBackground(this, labelLer, buttonLer, buttonSalvar);
        tarefa.execute();
        buttonLer.setEnabled(false);
        buttonSalvar.setEnabled(false);
        labelLer.setText("Lendo...");
    }

    private void salvarNoBD() {
        InsercaoBDBackground tarefa = new InsercaoBDBackground(area, buttonLer, buttonSalvar, instituicao);
        tarefa.execute();
        buttonLer.setEnabled(false);
        buttonSalvar.setEnabled(false);
        tarefa.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                if (arg0.getPropertyName().equals("progress")) {
                    progressBar.setValue((int) arg0.getNewValue());
                }
            }

        });
    }

    private void fechar() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JanelaXML::new);
//    	ManipuladorJAXB manipulador = new ManipuladorJAXB("servidores.xml");
//		Instituicao instituicao = manipulador.ler(Instituicao.class);
//		
//		List<Servidor> servidores = instituicao.getServidores();
//		
//		List<Servidor> outraLista = servidores.subList(0, 13000);
//		
//		instituicao.setServidores(outraLista);
//		
//		manipulador.gravar(instituicao);
    }
}
