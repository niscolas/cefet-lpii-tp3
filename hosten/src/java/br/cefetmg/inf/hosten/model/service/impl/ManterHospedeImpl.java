package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterHospede;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterHospedeImpl implements ManterHospede {
    HospedeDAO objetoDAO;

    public ManterHospedeImpl() {
        objetoDAO = HospedeDAO.getInstance();
    }
    
    @Override
    public boolean inserir(Hospede hospede) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, Hospede hospede) {
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
