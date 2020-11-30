package view;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import daos.ArtigoDao;
import entity.Artigo;
import entity.Conferencia;
import jaxb.ManipuladorJAXB;

public class TarefaBackground extends SwingWorker<Integer, Object> {

    private JLabel label;
    private JButton buttonOK;
    private ManipuladorJAXB manipulador;

    public TarefaBackground(JLabel label, JButton buttonOK, String arquivo) {
        this.label = label;
        this.buttonOK = buttonOK;
        manipulador = new ManipuladorJAXB(arquivo);
    }

    @Override
    protected Integer doInBackground() throws Exception {
        ArtigoDao artigoDao = new ArtigoDao();
        List<Artigo> artigos = artigoDao.getArtigosEAutores();
        Conferencia conferencia = new Conferencia(artigos);
        manipulador.gravar(conferencia);
        return artigos.size();
    }

    @Override
    public void done() {
        try {
            label.setText(String.valueOf(get()));
            buttonOK.setEnabled(true);
        } catch (ExecutionException erro) {
            label.setText("Erro durante a exportação");
        } catch (InterruptedException erro) {
            erro.printStackTrace();
        }
    }
}
