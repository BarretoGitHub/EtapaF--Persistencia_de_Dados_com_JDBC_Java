package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import entity.Dependente;
import entity.Servidor;
import utils.GerenciadorPool;

public class ServidorDao {

    private DataSource ds;

    public ServidorDao() {
        ds = GerenciadorPool.getInstance().getDataSource();
    }

    public void salvar(Servidor servidor) {
        String sql1 = "INSERT INTO Servidor (codigo,nome) values(?,?)";
        try (Connection con = ds.getConnection()) {
            con.setAutoCommit(false); //iniciamos a transação
            try (PreparedStatement pStat1 = con.prepareStatement(sql1)) {
                pStat1.setInt(1, servidor.getCodigo());
                pStat1.setString(2, servidor.getNome());
                pStat1.executeUpdate();

                salvarDependentes(servidor, con);

                con.commit();
            } catch (SQLException erro) {
                erro.printStackTrace();
                con.rollback();
                throw new RuntimeException(erro);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
    }

    private void salvarDependentes(Servidor servidor, Connection con) throws SQLException {
        String sql2 = "INSERT INTO Dependente (codigo_servidor,nome,tipo) values(?,?,?)";
        List<Dependente> dependentes = servidor.getDependentes();
        if (!dependentes.isEmpty()) {
            try (PreparedStatement pStat2 = con.prepareStatement(sql2)) {
                pStat2.setInt(1, servidor.getCodigo());
                for (Dependente dependente : dependentes) {
                    pStat2.setString(2, dependente.getNome());
                    pStat2.setString(3, dependente.getTipo().getSigla());
                    pStat2.executeUpdate();
                }
            }
        }
    }

    public int getTotalServidores() {
        String sql = "SELECT COUNT(*) from Servidor";
        try (Connection con = ds.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql);
                ResultSet rs = pStat.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

}
