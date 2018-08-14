package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterUsuario {
    public boolean inserir(Usuario usuario) throws NegocioException;
    public boolean alterar(String codRegistro, Usuario usuario) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<Usuario> buscar(Object dadoBusca, String coluna);
    public List<Usuario> buscarTodos();
}
