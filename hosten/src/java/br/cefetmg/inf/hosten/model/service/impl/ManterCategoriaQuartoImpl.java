package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterCategoriaQuarto;
import br.cefetmg.inf.util.exception.NegocioException;

public class ManterCategoriaQuartoImpl implements ManterCategoriaQuarto {
    CategoriaQuartoDAO objetoDAO;

    public ManterCategoriaQuartoImpl() {
        objetoDAO = CategoriaQuarto.getInstance();
    }
    
    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto) {
        //
        //
        //
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto) {
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
