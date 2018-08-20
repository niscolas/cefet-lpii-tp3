package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterServicoProxy implements IManterServico {

    @Override
    public boolean inserir(Servico servico) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Servico> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
