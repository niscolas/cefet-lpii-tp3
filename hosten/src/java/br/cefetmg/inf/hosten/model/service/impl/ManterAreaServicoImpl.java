package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterAreaServico;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterAreaServicoImpl implements ManterAreaServico {

    ServicoAreaDAO objetoDAO;
    
    public ManterAreaServicoImpl() {
        objetoDAO = ServicoAreaDAO.getInstance();
    }
    

    @Override
    public boolean inserir(ServicoArea servicoArea) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) {
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
