package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.QuartoDAO;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.ManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterQuartoImpl implements ManterQuarto {

    QuartoDAO objetoDAO;

    public ManterQuartoImpl() {
        objetoDAO = QuartoDAO.getInstance();
    }

    @Override
    public boolean inserir(Quarto quarto)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto)
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
    public List<Quarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Quarto> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
