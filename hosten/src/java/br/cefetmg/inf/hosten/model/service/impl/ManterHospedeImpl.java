package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.HospedeDAO;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.ManterHospede;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterHospedeImpl implements ManterHospede {

    HospedeDAO objetoDAO;

    public ManterHospedeImpl() {
        objetoDAO = HospedeDAO.getInstance();
    }

    @Override
    public boolean inserir(Hospede quarto)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, Hospede quarto)
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
    public List<Hospede> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Hospede> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
