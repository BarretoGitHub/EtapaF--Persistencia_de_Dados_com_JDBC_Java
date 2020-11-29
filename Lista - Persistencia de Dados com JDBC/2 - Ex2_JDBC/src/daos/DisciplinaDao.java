 
package daos;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import entity.Disciplina;
import utils.GerenciadorPool;

public class DisciplinaDao {
	
	private DataSource ds;
	
	public DisciplinaDao() {
		ds = GerenciadorPool.getInstance().getDataSource();
	}
    
    public void salvar(Disciplina disciplina) {
    	String sql1 = "INSERT INTO Disciplina (cod_disciplina,nome_disciplina) values(?,?)";
        try (Connection con = ds.getConnection())
        {
            con.setAutoCommit(false); //iniciamos a transação
            
            try (PreparedStatement pStat1 = con.prepareStatement(sql1))
            {
                pStat1.setString(1, disciplina.getCodigo());
                pStat1.setString(2, disciplina.getNome());
                pStat1.executeUpdate();
            
                salvarRequisitos(disciplina, con);
                        
                con.commit();
            }
            catch(SQLException erro) {
                con.rollback();
                throw new RuntimeException(erro);
            }
            finally {
            	con.setAutoCommit(false);
            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    private void salvarRequisitos(Disciplina disciplina, Connection con) throws SQLException {
    	String sql2 = "INSERT INTO Requisito (cod_disciplina,cod_requesito) values(?,?)";
        Set<Disciplina> reqs = disciplina.getRequisitos();
        if(!reqs.isEmpty()) {
            try (PreparedStatement pStat2 = con.prepareStatement(sql2))
            {
                for(Disciplina req: reqs) {
                    pStat2.setString(1, disciplina.getCodigo());
                    pStat2.setString(2, req.getCodigo());
                    pStat2.executeUpdate();
                }
            }
        }
    }
    
}
