package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterServico {
    public boolean inserir(Servico servico) throws NegocioException;
    public boolean alterar(String codRegistro, Servico servico) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<Servico> buscar(Object dadoBusca, String coluna);
    public List<Servico> buscarTodos();
}
