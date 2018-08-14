package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface ManterCategoriaQuarto {

    public boolean inserir(CategoriaQuarto categoriaQuarto) 
            throws NegocioException, SQLException;

    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException;
    public List<CategoriaQuarto> listarTodos()
            throws NegocioException, SQLException;

    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto) 
            throws NegocioException, SQLException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException;
}
