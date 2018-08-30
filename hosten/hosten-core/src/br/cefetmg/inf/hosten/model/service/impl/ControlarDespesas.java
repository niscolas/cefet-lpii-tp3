package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.rel.IQuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ControlarDespesas implements IControlarDespesas {

    @Override
    public boolean inserir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        IQuartoConsumoDAO quartoConsumoDAO = QuartoConsumoDAO.getInstance();
        if(quartoConsumo != null) {
            try {
                quartoConsumoDAO.adiciona(quartoConsumo);
                return true;
            } catch (SQLException e) {
            }
        } else {
            throw new NegocioException("O QuartoConsumo passado é inválido");
        }
        return false;
    }

    @Override
    public List<QuartoConsumo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        IQuartoConsumoDAO quartoConsumoDAO = QuartoConsumoDAO.getInstance();
        List<QuartoConsumo> quartosConsumoEncontrados = null;
        if (dadoBusca != null && coluna != null) {
            try {
                quartosConsumoEncontrados = quartoConsumoDAO.busca(dadoBusca, coluna);
            } catch(SQLException e) {
            }
        } else {
            throw new NegocioException("O dado utilizado para a busa, e / ou a coluna de parâmetro passado(s) é(são) inválido(s)");
        }
        return quartosConsumoEncontrados;
    }

    @Override
    public List<QuartoConsumo> listarTodos() throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean excluir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) throws NegocioException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
