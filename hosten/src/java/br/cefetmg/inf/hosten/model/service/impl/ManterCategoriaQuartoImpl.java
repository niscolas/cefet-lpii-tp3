package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.CategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.ManterCategoriaQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterCategoriaQuartoImpl implements ManterCategoriaQuarto {
    CategoriaQuartoDAO objetoDAO;

    public ManterCategoriaQuartoImpl() {
        objetoDAO = CategoriaQuartoDAO.getInstance();
    }
    
    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<CategoriaQuarto> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
