package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterCategoriaQuarto {
    public boolean inserir(CategoriaQuarto categoriaQuarto) throws NegocioException;
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<CategoriaQuarto> buscar(Object dadoBusca, String coluna);
    public List<CategoriaQuarto> buscarTodos();
}
