package br.cefetmg.inf.hosten.model.dao.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;

public interface IRelatorioDespesasDAO {
    List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
}
