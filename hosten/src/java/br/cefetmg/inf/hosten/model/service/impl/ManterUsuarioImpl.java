package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterUsuario;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterUsuarioImpl implements ManterUsuario {
    UsuarioDAO objetoDAO;

    public ManterUsuarioImpl() {
        objetoDAO = UsuarioDAO.getInstance();
    }
    

    @Override
    public boolean inserir(Usuario usuario) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario) {
        //
        //
        //
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException {
        //
        //
        //
    }

    @Override
    public Object buscar(Object dadoBusca, String coluna) {
        //
        //
        //
    }

    @Override
    public Object buscarTodos() {
        //
        //
        //
    }
    
}
