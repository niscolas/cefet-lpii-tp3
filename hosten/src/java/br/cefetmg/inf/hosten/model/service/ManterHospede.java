package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.util.exception.NegocioException;

public interface ManterHospede {
    public boolean inserir(Hospede hospede) throws NegocioException;
    public boolean alterar(String codRegistro, Hospede hospede) throws NegocioException;
    public boolean excluir(String codRegistro) throws NegocioException;
    public List<Hospede> buscar(Object dadoBusca, String coluna);
    public List<Hospede> buscarTodos();
}
