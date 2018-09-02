/*
    Confirma a operação de check-out
    Direciona para o arquivo pdf --> fatura
*/
package br.cefetmg.inf.hosten.controller.hospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
import javax.servlet.http.HttpServletRequest;

public class ConfirmarCheckOut {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String nroQuarto = request.getParameter("nroQuarto");
            
            IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();
            int seqHospedagem = controlarHosp.efetuarCheckOut(nroQuarto);
            
            request.setAttribute("seqHospedagem", seqHospedagem);
            
            jsp = "/fatura-gerada.pdf";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    } 
}
