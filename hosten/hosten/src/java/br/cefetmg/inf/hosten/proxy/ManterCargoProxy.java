package br.cefetmg.inf.hosten.proxy;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterCargoProxy implements IManterCargo {

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cargo> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
