package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/*
    Salva o número do quarto na requisição
    Lista as despesas
    Redireciona para a página de check-out
*/
public class CheckOut {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String nroQuarto = request.getParameter("nroQuarto");
            
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            int seqHospedagem = manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(Integer.parseInt(nroQuarto));
            
            IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
            Map<String, Object> listaDespesas;
            listaDespesas = controlarDespesas.retornaRelatorioDespesas(seqHospedagem, Integer.parseInt(nroQuarto));
            
            request.setAttribute("listaDespesas", listaDespesas);
            
            jsp = "/view/check-out.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
