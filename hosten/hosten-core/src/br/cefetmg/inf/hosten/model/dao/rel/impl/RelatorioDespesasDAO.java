package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.IRelatorioDespesasDAO;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;

public class RelatorioDespesasDAO implements IRelatorioDespesasDAO {
    
    private Connection con;
    private static RelatorioDespesasDAO instancia;
    
    private RelatorioDespesasDAO() {
        con = new ConnectionFactory().getConnection();
    }
    
    public static synchronized RelatorioDespesasDAO getInstance() {
        if (instancia == null) {
            instancia  = new RelatorioDespesasDAO();
        }
        return instancia;
    }
    
    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        String qry = "SELECT * "
                + "FROM RelatorioDespesas "
                + "WHERE "
                + "seqHospedagem = ? AND "
                + "nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        ResultSet rs = pStmt.executeQuery();

        List<Despesa> despesaEncontradas = new ArrayList<>();

        int i = 0;
        /* 
        int seqHospedagem, int nroQuarto, int nroAdultos, int nroCriancas, 
        Double vlrDiaria, 
        Timestamp datCheckIn, Timestamp datCheckOut, 
        Double vlrPago, 
        String nomeHospede, 
        int seqServico, int qtdConsumo, 
        String desServico, 
        Double vlrUnit
        */
        while (rs.next()) {
            despesaEncontradas
                    .add(new Despesa(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getTimestamp(6),
                            rs.getTimestamp(7),
                            rs.getDouble(8),
                            rs.getString(9),
                            rs.getInt(10),
                            rs.getInt(11),
                            rs.getString(12),
                            rs.getDouble(13)));
            i++;
        }
        return despesaEncontradas;
    }
}
