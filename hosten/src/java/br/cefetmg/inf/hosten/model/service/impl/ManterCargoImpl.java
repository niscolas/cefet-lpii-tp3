package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.CargoDAO;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.ManterCargo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterCargoImpl implements ManterCargo {
    CargoDAO objetoDAO;
    
    public ManterCargoImpl() {
        objetoDAO = CargoDAO.getInstance();
    }
    
    @Override
    public boolean inserir(Cargo categoriaQuarto)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, Cargo categoriaQuarto)
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
    public List<Cargo> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Cargo> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
