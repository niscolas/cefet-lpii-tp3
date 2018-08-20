package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterQuartoProxy implements IManterQuarto {

    @Override
    public boolean inserir(Quarto quarto) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quarto> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
