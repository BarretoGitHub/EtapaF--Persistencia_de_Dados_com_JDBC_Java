package view;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import dao.ServidorDao;
import entity.Instituicao;
import entity.Servidor;

//Classe responsável pelo armazenamento de dados dos servidores no banco de dados
//Esta operação deve ser executada em uma thread trabalhadora
public class InsercaoBDBackground extends SwingWorker<Void, Integer> {

    private JTextArea area;
    private JButton buttonLer;
    private JButton buttonSalvar;
    private Instituicao instituicao;

    public InsercaoBDBackground(JTextArea area, JButton buttonLer, JButton buttonSalvar, Instituicao instituicao) {
        this.area = area;
        this.buttonLer = buttonLer;
        this.buttonSalvar = buttonSalvar;
        this.instituicao = instituicao;
    }

    @Override
    protected Void doInBackground() throws Exception {
        List<Servidor> servidores = instituicao.getServidores();
        ServidorDao servidorDao = new ServidorDao();
        for (int i = 0; i < servidores.size(); i++) {
            Servidor servidor = servidores.get(i);
            servidorDao.salvar(servidor);
            publish(servidor.getCodigo()); //para publicar os resultados intermediários
            if ((i + 1) % 100 == 0) {
                setProgress(100 * (i + 1) / servidores.size());
            }
        }
        if (servidores.size() % 100 != 0) {
            setProgress(100);
        }
        return null;
    }

    @Override
    protected void done() {
        //apenas para testes
        ServidorDao servidorDao = new ServidorDao();
        int total = servidorDao.getTotalServidores();
        System.out.println(total);
        buttonLer.setEnabled(true);
        buttonSalvar.setEnabled(true);
    }

    @Override
    protected void process(List<Integer> codigos) {
        for (Integer codigo : codigos) {
            area.append(String.valueOf(codigo));
            area.append("\n");
        }
    }
}
