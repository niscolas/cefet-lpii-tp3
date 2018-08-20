package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterServicoAreaProxy implements IManterServicoArea {

    @Override
    public boolean inserir(ServicoArea servicoArea) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}