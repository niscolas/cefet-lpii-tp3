package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarDetalhesConta {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String nroQuarto = request.getParameter("nroQuarto");
            
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            int seqHospedagem = manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(Integer.parseInt(nroQuarto));
            
            IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
            List<Despesa> listaDespesas;
            listaDespesas = controlarDespesas.listar(seqHospedagem, seqHospedagem);
            
            request.setAttribute("listaDespesas", listaDespesas);
            
            jsp = "/view/conta-detalhes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
