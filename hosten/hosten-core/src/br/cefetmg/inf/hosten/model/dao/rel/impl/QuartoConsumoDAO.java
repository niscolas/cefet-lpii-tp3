package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.IQuartoConsumoDAO;
import java.util.HashMap;
import java.util.Map;

public class QuartoConsumoDAO implements IQuartoConsumoDAO {

    private static QuartoConsumoDAO instancia;
    private static Connection con;

    private QuartoConsumoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoConsumoDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoConsumoDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        String qry = "INSERT INTO "
                + "QuartoConsumo(seqHospedagem, nroQuarto, datConsumo,"
                + "qtdConsumo, seqServico, codUsuarioRegistro) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, quartoConsumo.getSeqHospedagem());
        pStmt.setInt(2, quartoConsumo.getNroQuarto());
        pStmt.setTimestamp(3, quartoConsumo.getDatConsumo());
        pStmt.setInt(4, quartoConsumo.getQtdConsumo());
        pStmt.setInt(5, quartoConsumo.getSeqServico());
        pStmt.setString(6, quartoConsumo.getCodUsuarioRegistro());
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<QuartoConsumo> busca(Object dadoBusca, String coluna)
            throws SQLException {
        String qry = "SELECT * "
                + "FROM QuartoConsumo "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        if (dadoBusca instanceof String) {
            pStmt.setString(1, dadoBusca.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(dadoBusca.toString()));
        }

        ResultSet rs = pStmt.executeQuery();

        List<QuartoConsumo> quartoConsumoEncontrados = new ArrayList<>();

        rs.beforeFirst();
        int i = 0;
        while (rs.next()) {
            quartoConsumoEncontrados
                    .add(new QuartoConsumo(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getTimestamp(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6)));
            i++;
        }
        return quartoConsumoEncontrados;
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto,
            Timestamp datConsumo) throws SQLException {
        String qry = "DELETE FROM QuartoConsumo "
                + "WHERE seqHospedagem = ? AND nroQuarto = ? AND datConsumo = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        pStmt.setTimestamp(3, datConsumo);
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(QuartoConsumo quartoConsumo) throws SQLException {
        String qry = "DELETE FROM QuartoConsumo "
                + "WHERE seqHospedagem = ? AND nroQuarto = ? AND datConsumo = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, quartoConsumo.getSeqHospedagem());
        pStmt.setInt(2, quartoConsumo.getNroQuarto());
        pStmt.setTimestamp(3, quartoConsumo.getDatConsumo());
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Map<String, Object> retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) 
            throws SQLException {
        String qry = "SELECT * "
                + "FROM  relatorioDespesas "
                + "WHERE seqHospedagem = ? AND nroQuarto = ?";
        PreparedStatement pStmt = con.prepareStatement(qry, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        ResultSet rs = pStmt.executeQuery();

        
        Map<String, Object> map = new HashMap<>();
        
        rs.next();
        map.put("seqHospedagem", rs.getInt("seqHospedagem"));
        map.put("nroQuarto", rs.getInt("nroQuarto"));
        map.put("nroAdultos", rs.getInt("nroAdultos"));
        map.put("nroCriancas", rs.getInt("nroCriancas"));
        map.put("vlrDiaria", rs.getDouble("vlrDiaria"));
        map.put("datCheckIn", rs.getTimestamp("datCheckIn"));
        map.put("datCheckOut", rs.getTimestamp("datCheckOut"));
        map.put("vlrPago", rs.getDouble("vlrPago"));
        map.put("nomHospede", rs.getString("nomHospede"));
        rs.beforeFirst();
        
        //
        //
        ArrayList despesas = new ArrayList();
        
        while(rs.next()) {            
            Map<String, Object> despesa = new HashMap<>();
            map.put("seqServico", rs.getInt("seqServico"));
            map.put("desServico", rs.getString("desServico"));
            map.put("qtdConsumo", rs.getInt("qtdConsumo"));
            map.put("vlrUnit", rs.getDouble("vlrUnit"));
            
            despesas.add(despesa);
        }
        
        map.put("arrayDespesas", despesas);
        
        return map;
    }
}
