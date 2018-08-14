package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterAreaServico {
    public boolean inserir(ServicoArea servicoArea) throws NegocioException;
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<ServicoArea> buscar(Object dadoBusca, String coluna);
    public List<ServicoArea> buscarTodos();
}
