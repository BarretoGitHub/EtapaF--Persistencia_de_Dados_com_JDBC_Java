package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;

import entity.Artigo;
import entity.Autor;
import utils.GerenciadorPool;

public class ArtigoDao {

    private DataSource ds;

    public ArtigoDao() {
        ds = GerenciadorPool.getInstance().getDataSource();
    }

    public List<Artigo> getArtigosEAutores() {
        String sql = "SELECT ar.titulo, ar.pag_inicial, ar.pag_final, au.id, au.nome"
                + "   FROM prova2_artigo ar INNER item it"
                + "   ON ar.titulo = it.titulo_artigo"
                + "   INNER JOIN autor au"
                + "   ON it.id_autor = au.id";

        try (Connection con = ds.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql)) {
            return executarConsulta(pStat);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Artigo> executarConsulta(PreparedStatement pStat) throws SQLException {
        Map<String, Artigo> artigosProcessados = new HashMap<>();
        Map<Integer, Autor> autoresProcessados = new HashMap<>();

        try (ResultSet rs = pStat.executeQuery()) {
            while (rs.next()) {
                //Dados de artigos
                String titulo = rs.getString("titulo");
                int pagInicial = rs.getInt("pag_inicial");
                int pagFinal = rs.getInt("pag_final");

                //Dados de autores
                int idAutor = rs.getInt("id");
                String nomeAutor = rs.getString("nome");

                //Se o artigo existir, apenas recuperamos a sua referência conforme a chave (titulo)
                //Se o artigo não existir, ele será instanciado e armazenado no map, e sua referência será recuperada
                Artigo artigo = artigosProcessados.computeIfAbsent(titulo, tit -> new Artigo(tit, pagInicial, pagFinal));

                //O mesmo procedimento para artigos aplica-se aos autores
                Autor autor = autoresProcessados.computeIfAbsent(idAutor, id -> new Autor(id, nomeAutor));

                artigo.addAutor(autor);
            }

            return artigosProcessados.values().stream().collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
