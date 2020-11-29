package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import entity.Servidor;
import utils.GerenciadorPool;

public class ServidorDao {

    private DataSource ds;

    public ServidorDao() {
        ds = GerenciadorPool.getInstance().getDataSource();
    }

    public List<Servidor> listarTodosOsServidoresEChefes() {

        String sql2 = "SELECT s.cpf_servidor AS CPF_Servidor, s.nome_servidor AS NOME_Servidor, s.cpf_chefe AS CPF_Chefe,"
                + " c.cpf_servidor AS CPF_Subordinado, c.nome_servidor as NOME_Subordinado FROM Servidor s LEFT JOIN Servidor c"
                + " ON s.cpf_servidor = c.cpf_chefe";

        try (Connection con = ds.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql2)) {
            return executarConsulta(pStat);
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    private List<Servidor> executarConsulta(PreparedStatement pStat) throws SQLException {
        Map<String, Servidor> servidoresProcessados = new HashMap<>();

        List<Servidor> servidores = new ArrayList<>();

        try (ResultSet rs = pStat.executeQuery()) {

            while (rs.next()) {
                String cpfServidor = rs.getString("cpf_servidor");
                String nomeServidor = rs.getString("nome_servidor");
                String cpfChefe = rs.getString("cpf_chefe");

                //Cada linha devolvida pela query representa um servidor
                //Verificamos se o servidor corrente já não existe no map de servidores
                //A computação abaixo somente será realizada se o servidor existir no map de servidores
                Servidor servidor = servidoresProcessados.computeIfPresent(cpfServidor, (cpf, serv) -> {
                    if (serv.getNome() == null) {
                        serv.setNome(nomeServidor);
                    }
                    return serv;
                });

                //Servidor não existe no map de servidores
                if (servidor == null) {
                    servidor = new Servidor(cpfServidor, nomeServidor);
                    servidoresProcessados.put(cpfServidor, servidor);
                }

                if (cpfChefe != null) {
                    //Então o servidor possui um chefe 
                    //Precisamos saber se ele já existe ou se devemos instanciá-lo
                    Servidor chefe = servidoresProcessados.computeIfAbsent(cpfChefe, cpf -> new Servidor(cpf));
                    servidor.setChefe(chefe);
                }
                servidores.add(servidor);
            }

            return servidores;
        }
    }
}
