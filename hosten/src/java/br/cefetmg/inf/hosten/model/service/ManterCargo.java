package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterCargo {
    public boolean inserir(Cargo cargo) throws NegocioException;
    public boolean alterar(String codRegistro, Cargo cargo) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<Cargo> buscar(Object dadoBusca, String coluna);
    public List<Cargo> buscarTodos();
}
