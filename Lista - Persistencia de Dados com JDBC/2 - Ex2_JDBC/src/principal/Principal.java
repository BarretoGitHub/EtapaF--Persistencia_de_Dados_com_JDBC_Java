package principal;
 
import daos.DisciplinaDao;
import entity.Disciplina;

public class Principal {
    
    public static void main(String args[]) {	
    	Disciplina poo = new Disciplina("POO", "Programação Orientada a Objetos");
        Disciplina aoo = new Disciplina("AOO", "Análise Orientada a Objetos");
        Disciplina bd = new Disciplina("BD", "Banco de Dados ");
        
        Disciplina dsi = new Disciplina("DSI", "Desenvolvimento de Sistemas");
        dsi.addRequisito(poo);
        dsi.addRequisito(aoo);
        dsi.addRequisito(bd);
        
        Disciplina dw = new Disciplina("DW", "Desenvolvimento para Web ");
        dw.addRequisito(dsi);
        DisciplinaDao dao = new DisciplinaDao();
        
        try {
            dao.salvar(poo);
            dao.salvar(aoo);
            dao.salvar(bd);
            dao.salvar(dsi);
            dao.salvar(dw);
        }
        catch(Exception erro) {
            erro.printStackTrace();
        }
    }
}
