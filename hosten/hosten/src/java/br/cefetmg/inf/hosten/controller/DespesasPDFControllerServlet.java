package br.cefetmg.inf.hosten.controller;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import static com.itextpdf.text.Font.FontFamily.HELVETICA;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DespesasPDFControllerServlet extends HttpServlet {
    private HttpServletRequest requestInterno;
    private Document document;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestInterno = request;
        response.setContentType("application/pdf");
        try {
            document = new Document(PageSize.A4, 90, 90, 90, 90);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            montaArquivo();
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            //
            //
            //
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void montaArquivo() throws DocumentException, SQLException, NegocioException {
        int seqHospedagem = (Integer)(requestInterno.getAttribute("seqHospedagem"));
        int nroQuarto = Integer.parseInt(requestInterno.getParameter("nroQuarto"));
        
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
        List<Despesa> listaDespesas = controlarDespesas.listar(seqHospedagem, nroQuarto);

        // monta o arquivo
        DottedLineSeparator separator = new DottedLineSeparator();
        Font fonteUltraViolet = new Font(HELVETICA, 20, 1, new BaseColor(95, 75, 139));
        Font fonteRedViolet = new Font(HELVETICA, 18, 1, new BaseColor(163, 87, 118));
        Font fonteSparklingGrape = new Font(HELVETICA, 14, 1, new BaseColor(125, 63, 124));
        Font fonteMulberry = new Font(HELVETICA, 14, 0, new BaseColor(167, 108, 151));
        Font fonteChateauRose = new Font(HELVETICA, 14, 0, new BaseColor(210, 115, 143));
        Font fonteChateauRoseNegrito = new Font(HELVETICA, 14, 1, new BaseColor(210, 115, 143));
        
        Chunk c = new Chunk(separator);
        Paragraph p = new Paragraph();
        
        // título
        p.setFont(fonteUltraViolet); p.add("Fatura da hospedagem"); document.add(p); p.clear();
        
        // subtítulo com o nome do cliente
        String nomeHospede = listaDespesas.get(0).getNomeHospede();
        
        p.setFont(fonteRedViolet); p.setSpacingBefore(20); p.setSpacingAfter(20);
        p.add(nomeHospede); document.add(p); p.clear();
        
        Double vlrTotal = 0.0;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        
        for (Despesa despesa : listaDespesas) {
            // um parágrafo para cada item/servico consumido
            int qtdServico = despesa.getQtdConsumo();
            String desServico = despesa.getDesServico();
            Double vlrServico = despesa.getVlrUnit();
            
            vlrTotal += vlrServico*qtdServico;

            String strVlrServico = currencyFormatter.format(vlrServico);

            p.setFont(fonteMulberry); p.setSpacingAfter(2); p.setSpacingBefore(0);
            p.add(String.valueOf(qtdServico)); p.add(" "); p.add(desServico);
            p.add(c); p.setFont(fonteSparklingGrape); p.add(strVlrServico);
            document.add(p);
            p.clear();
        }

        // parágrafo com as informações da diária
        int nroAdultos = listaDespesas.get(0).getNroAdultos();
        int nroCriancas = listaDespesas.get(0).getNroCriancas();
        Double vlrDiaria = listaDespesas.get(0).getVlrDiaria();

        Timestamp datCheckIn = listaDespesas.get(0).getDatCheckIn();
        Timestamp datCheckOut = listaDespesas.get(0).getDatCheckOut();
        long msDiferenca = (datCheckOut.getTime()) - (datCheckIn.getTime());
        long segundos = msDiferenca/1000;
        long minutos = segundos/60;
        long horas = minutos/60;
        long dias = horas/24;

        Double valorDiarias = dias*vlrDiaria;
        vlrTotal += valorDiarias;

        p.setFont(fonteChateauRose); p.setSpacingAfter(2); p.setSpacingBefore(0);

        p.add("Número de adultos"); p.add(c); p.add(String.valueOf(nroAdultos));
        document.add(p); p.clear();

        p.add("Número de crianças"); p.add(c); p.add(String.valueOf(nroCriancas));
        document.add(p); p.clear();

        p.add("Dias de estadia"); p.add(c); p.add(String.valueOf(dias));
        document.add(p); p.clear();

        p.add("Valor total das diárias"); p.add(c);
        
        String strValorDiarias = currencyFormatter.format(valorDiarias);
        p.setFont(fonteChateauRoseNegrito); p.add(strValorDiarias);
        document.add(p); p.clear();
        
        // parágrafo com o valor total
        String strValorTotal = currencyFormatter.format(vlrTotal);
        p.setFont(fonteRedViolet);
        p.setSpacingBefore(20);
        p.add("Valor total");
        p.add(c);
        p.add(strValorTotal);
        document.add(p);
    }
}