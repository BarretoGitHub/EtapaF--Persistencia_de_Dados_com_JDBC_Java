package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.Month;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entity.Colaborador;
import entity.ListaColaborador;

public class JanelaListagem extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JComboBox<Month> comboMeses;
    private JTable table;
    private DefaultTableModel defaultModel;
    private JScrollPane scroll;
    private JButton buttonBuscar;
    private JButton buttonFechar;

    private ListaColaborador lista;

    public JanelaListagem(List<Colaborador> colaboradores) {
        super("Aniversários");
        lista = new ListaColaborador(colaboradores);
        criarComponentes();
        configurarJanela();
    }

    private void criarComponentes() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Listagem de aniversários"));

        comboMeses = new JComboBox<>(Month.values());

        defaultModel = new DefaultTableModel();
        table = new JTable();
        table.setModel(defaultModel);
        table.setFillsViewportHeight(true);

        defaultModel.addColumn("Nome do colaborador");
        defaultModel.addColumn("Data de aniversário");

        scroll = new JScrollPane();
        scroll.setViewportView(table);

        scroll.setPreferredSize(new Dimension(400, 300));

        buttonBuscar = new JButton("Buscar");
        buttonBuscar.addActionListener(this::buscarAniversariantes);

        buttonFechar = new JButton("Fechar");
        buttonFechar.addActionListener(this::fechar);

        panel.add(comboMeses);
        panel.add(buttonBuscar);
        panel.add(buttonFechar);
        panel.add(scroll);
        add(panel);
    }

    private void configurarJanela() {
        setVisible(true);
        //setSize(600,200);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void buscarAniversariantes(ActionEvent e) {
        Month month = comboMeses.getItemAt(comboMeses.getSelectedIndex());
        List<Colaborador> colaboradores = lista.getColaboradoresPorMes(month);
        defaultModel.setNumRows(0);
        colaboradores.forEach(c -> defaultModel.addRow(c.toArray()));
    }

    private void fechar(ActionEvent e) {
        System.exit(0);
    }
}
