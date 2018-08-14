package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.ServicoAreaDAO;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.service.ManterServicoArea;

public class ManterServicoAreaImpl implements ManterServicoArea {

    ServicoAreaDAO objetoDAO;
    
    public ManterServicoAreaImpl() {
        objetoDAO = ServicoAreaDAO.getInstance();
    }
    
    @Override
    public boolean inserir(ServicoArea servicoArea)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea)
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
    public List<ServicoArea> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<ServicoArea> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
