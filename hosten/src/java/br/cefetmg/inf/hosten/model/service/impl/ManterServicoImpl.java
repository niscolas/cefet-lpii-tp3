package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterServico;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterServicoImpl implements ManterServico {
    ServicoDAO objetoDAO;
    
    public ManterServicoImpl() {
        objetoDAO = ServicoDAO.getInstance();
    }
    
    @Override
    public boolean inserir(Servico servico) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico) {
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
