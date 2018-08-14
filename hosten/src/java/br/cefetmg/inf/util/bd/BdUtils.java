package br.cefetmg.inf.util.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class BdUtils {

    private static Connection con = new ConnectionFactory().getConnection();

    public static int contaLinhasResultSet(ResultSet rs) throws SQLException {
        rs.last();
        int nroLinhas = rs.getRow();

        return nroLinhas;
    }

//    public static int buscaUltimoRegistroRelacionadoAoQuarto(int nroQuarto)
//            throws SQLException {
//        String qry = "SELECT A.seqHospedagem "
//                + "FROM Hospedagem A "
//                + "JOIN QuartoHospedagem B ON A.seqHospedagem = B.seqHospedagem "
//                + "WHERE B.nroQuarto = ? "
//                + "ORDER BY A.datCheckIn DESC "
//                + "LIMIT 1";
//        PreparedStatement pStmt = con.prepareStatement(qry);
//        pStmt.setInt(1, nroQuarto);
//        ResultSet rs = pStmt.executeQuery();
//
//        if (rs.next())
//            return rs.getInt(1);
//        else
//            return 0;
//    }
    
//    public static ResultSet retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) throws SQLException {
//        String qry = "SELECT * "
//                + "FROM  relatorioDespesas "
//                + "WHERE seqHospedagem = ? AND nroQuarto = ?";
//        PreparedStatement pStmt = con.prepareStatement(qry, ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_UPDATABLE);
//        pStmt.setInt(1, seqHospedagem);
//        pStmt.setInt(2, nroQuarto);
//        ResultSet rs = pStmt.executeQuery();
//
//        return rs;
//    }

    public static void apagarBDHosten() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("DROP SCHEMA public CASCADE;"
                + "CREATE SCHEMA public;"
                + "GRANT ALL ON SCHEMA public TO postgres;"
                + "GRANT ALL ON SCHEMA public TO public;");
    }
}
