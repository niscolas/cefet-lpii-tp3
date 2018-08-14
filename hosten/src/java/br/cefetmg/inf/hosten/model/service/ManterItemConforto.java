package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;
import br.cefetmg.inf.hosten.domain.ItemConforto;

public interface ManterItemConforto {
    public boolean inserir(ItemConforto itemConforto) throws NegocioException;
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<ItemConforto> buscar(Object dadoBusca, String coluna);
    public List<ItemConforto> buscarTodos();
}
