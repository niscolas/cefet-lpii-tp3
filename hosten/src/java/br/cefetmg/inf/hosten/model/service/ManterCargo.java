package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface ManterCargo {

    public boolean inserir(Cargo cargo) 
            throws NegocioException, SQLException;

    public List<Cargo> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException;
    public List<Cargo> listarTodos()
            throws NegocioException, SQLException;

    public boolean alterar(String codRegistro, Cargo cargo) 
            throws NegocioException, SQLException;

    public boolean excluir(String codRegistro) 
            throws NegocioException, SQLException;
}
