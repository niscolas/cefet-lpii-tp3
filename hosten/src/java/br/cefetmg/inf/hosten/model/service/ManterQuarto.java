package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterQuarto {
    public boolean inserir(Quarto quarto) throws NegocioException;
    public boolean alterar(String codRegistro, Quarto quarto) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<Quarto> buscar(Object dadoBusca, String coluna);
    public List<Quarto> buscarTodos();
}
