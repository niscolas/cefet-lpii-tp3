package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract class BaseDAO<ObjetoPOJO> {

    protected static Connection con;

    protected BaseDAO() {
        con = new ConnectionFactory().getConnection();
    }

    protected abstract boolean adiciona(ObjetoPOJO obj)
            throws SQLException, NoSuchAlgorithmException,
            UnsupportedEncodingException;

    protected abstract List<ObjetoPOJO> busca(Object dadoBusca, String coluna)
            throws SQLException, NoSuchAlgorithmException,
            UnsupportedEncodingException;

    protected abstract List<ObjetoPOJO> buscaTodos()
            throws SQLException, NoSuchAlgorithmException,
            UnsupportedEncodingException;

    protected abstract boolean atualiza(Object pK, ObjetoPOJO objAtualizado)
            throws SQLException, NoSuchAlgorithmException,
            UnsupportedEncodingException;

    protected abstract boolean deleta(Object pK) throws SQLException;
}
