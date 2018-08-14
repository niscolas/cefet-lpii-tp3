package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterCargo;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterCargoImpl implements ManterCargo {
    CargoDAO objetoDAO;
    
    public ManterCargoImpl() {
        objetoDAO = CargoDAO.getInstance();
    }
    

    @Override
    public boolean inserir(Cargo cargo) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo) {
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
