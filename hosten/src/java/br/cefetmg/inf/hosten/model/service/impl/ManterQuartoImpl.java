package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterQuartoImpl implements ManterQuarto {
    QuartoDAO objetoDAO;

    public ManterQuartoImpl() {
        objetoDAO = QuartoDAO.getInstance();
    }
    
    @Override
    public boolean inserir(Quarto quarto) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto) {
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
