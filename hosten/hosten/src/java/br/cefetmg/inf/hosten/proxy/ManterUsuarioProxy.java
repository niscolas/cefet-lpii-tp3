package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterUsuarioProxy implements IManterUsuario {

    @Override
    public boolean inserir(Usuario usuario) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario usuarioLogin(String email, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
