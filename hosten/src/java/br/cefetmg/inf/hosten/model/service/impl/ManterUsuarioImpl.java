package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.UsuarioDAO;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.ManterUsuario;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterUsuarioImpl implements ManterUsuario {

    UsuarioDAO objetoDAO;

    public ManterUsuarioImpl() {
        objetoDAO = UsuarioDAO.getInstance();
    }

    @Override
    public boolean inserir(Usuario usuario)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario)
            throws NegocioException, SQLException {
        //TODO
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) 
        throws NegocioException, SQLException{
        //TODO
        return false;
    }

    @Override
    public List<Usuario> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Usuario> listarTodos()
            throws NegocioException, SQLException {
        //TODO
        return new ArrayList<>();
    }
}
