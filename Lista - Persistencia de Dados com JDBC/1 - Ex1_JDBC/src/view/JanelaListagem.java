package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ServidorDao;
import entity.Servidor;

public class JanelaListagem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JButton buttonBuscar;
    private JButton buttonFechar;

    public final static int INTERVALO = 5;

    public JanelaListagem() {
        super("Servidores");
        criarComponentes();
        configurarJanela();
    }

    private void criarComponentes() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Listagem de servidores"));

        model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
        table.setFillsViewportHeight(true);

        model.addColumn("CPF do servidor");
        model.addColumn("Nome do servidor");
        model.addColumn("CPF do chefe");
        model.addColumn("Nome do chefe");

        scroll = new JScrollPane();
        scroll.setViewportView(table);

        scroll.setPreferredSize(new Dimension(600, 400));

        buttonBuscar = new JButton("Buscar");
        buttonBuscar.addActionListener(this::buscarServidoresEChefes);

        buttonFechar = new JButton("Fechar");
        buttonFechar.addActionListener(this::fechar);

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

    private void buscarServidoresEChefes(ActionEvent e) {
        ServidorDao servidorDao = new ServidorDao();
        List<Servidor> servidores = servidorDao.listarTodosOsServidoresEChefes();
        model.setNumRows(0);
        servidores.forEach(s -> {
            model.addRow(s.toArray());
        });

        //para testes
        servidores.forEach(s -> {
            System.out.println("Nome:" + s.getNome());
            System.out.println("Código do chefe:" + s.getChefe());
            System.out.println();

        });
    }

    private void fechar(ActionEvent e) {
        System.exit(0);
    }
}
