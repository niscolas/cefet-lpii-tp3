package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
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
            List<Despesa> listaDespesas;
            listaDespesas = controlarDespesas.listar(seqHospedagem, seqHospedagem);
            
            request.setAttribute("listaDespesas", listaDespesas);
            
            jsp = "/view/check-out.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
