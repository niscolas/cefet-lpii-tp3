package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.ICategoriaItemConfortoDAO;

public class CategoriaItemConfortoDAO implements ICategoriaItemConfortoDAO {

    private static CategoriaItemConfortoDAO instancia;
    private static Connection con;

    private CategoriaItemConfortoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized CategoriaItemConfortoDAO getInstance() {
        if (instancia == null) {
            instancia = new CategoriaItemConfortoDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaItemConforto categoriaItemConforto)
            throws SQLException {
        String qry = "INSERT INTO "
                + "CategoriaItemConforto(codCategoria, codItem) "
                + "VALUES(?,?)";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, categoriaItemConforto.getCodCategoria());
        pStmt.setString(2, categoriaItemConforto.getCodItem());
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<CategoriaItemConforto> busca(String cod, String coluna) 
            throws SQLException {
        String qry;
        if (coluna.equals("codItem")) {
            qry = "SELECT * FROM CategoriaItemConforto "
                    + "WHERE codItem = ?";
        } else {
            qry = "SELECT * FROM CategoriaItemConforto "
                    + "WHERE codCategoria = ?";
        }
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, cod);

        ResultSet rs = pStmt.executeQuery();

        List<CategoriaItemConforto> categoriaItemConfortoEncontrados
                = new ArrayList<>();

        int i = 0;
        while (rs.next()) {
            categoriaItemConfortoEncontrados
                    .add(new CategoriaItemConforto(
                            rs.getString(1),
                            rs.getString(2)));
            i++;
        }
        return categoriaItemConfortoEncontrados;
    }

    @Override
    public boolean deletaPorColuna(String dadoBusca, String coluna) 
            throws SQLException {
        String qry = "DELETE FROM CategoriaItemConforto "
                + "WHERE " + coluna + " = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, dadoBusca);
        return pStmt.executeUpdate() > 0;
    }
}
