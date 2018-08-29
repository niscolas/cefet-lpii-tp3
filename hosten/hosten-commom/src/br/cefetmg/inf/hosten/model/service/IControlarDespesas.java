package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface IControlarDespesas {
    public boolean inserir(QuartoConsumo quartoConsumo) 
            throws NegocioException, SQLException;

    public List<QuartoConsumo> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException;
    public List<QuartoConsumo> listarTodos()
            throws NegocioException, SQLException;

    public boolean alterar(QuartoConsumo quartoConsumoAntigo, QuartoConsumo quartoConsumoNovo) 
            throws NegocioException, SQLException;

    public boolean excluir(QuartoConsumo quartoConsumo) 
            throws NegocioException, SQLException;
    
}
