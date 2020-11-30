package view;

import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import entity.Instituicao;
import jaxb.ManipuladorJAXB;

//Classe responsável pela leitura do XML
//Esta operação deve ser executada em uma thread trabalhadora
public class LeituraXMLBackground extends SwingWorker<Instituicao, Object> {

    private JanelaXML janela;
    private JLabel labelLer;
    private JButton buttonLer;
    private JButton buttonSalvar;

    public LeituraXMLBackground(JanelaXML janela, JLabel labelLer, JButton buttonLer, JButton buttonSalvar) {
        this.janela = janela;
        this.labelLer = labelLer;
        this.buttonLer = buttonLer;
        this.buttonSalvar = buttonSalvar;
    }

    @Override
    protected Instituicao doInBackground() throws Exception {
        ManipuladorJAXB manipulador = new ManipuladorJAXB("servidores.xml");
        Instituicao instituicao = manipulador.ler(Instituicao.class);
        return instituicao;
    }

    @Override
    protected void done() {
        buttonLer.setEnabled(true);
        buttonSalvar.setEnabled(true);
        try {
            Instituicao instituicao = get();
            janela.setInstituicao(instituicao);
            labelLer.setText(String.valueOf(instituicao.getServidores().size()));
        } catch (ExecutionException | InterruptedException erro) {
            labelLer.setText("Erro durante a leitura");
            erro.printStackTrace();
        }
    }

}
