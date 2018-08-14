package br.cefetmg.inf.hosten.model.dao.rel;

import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import java.sql.SQLException;
import java.util.List;

public interface CategoriaItemConfortoDAO {
    boolean adiciona(CategoriaItemConforto categoriaItemConforto) 
            throws SQLException;
    
    List<CategoriaItemConforto> busca(String dadoBusca, String coluna) 
            throws SQLException;
    
    //atualiza();
    
    boolean deletaPorColuna(String dadoBusca, String coluna) 
            throws SQLException;
}
