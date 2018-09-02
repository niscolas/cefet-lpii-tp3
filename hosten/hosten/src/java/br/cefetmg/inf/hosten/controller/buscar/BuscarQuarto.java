package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarQuarto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String nroQuarto = request.getParameter("nroQuarto");
        
        IManterQuarto manterQuarto = new ManterQuartoProxy();
        
        try {
            List<Quarto> listaQuartos = manterQuarto.listar(nroQuarto, "nroQuarto");
            
            if (!listaQuartos.isEmpty()) {
                Quarto quarto = listaQuartos.get(0);
                request.setAttribute("quarto", quarto);
            }
            
            jsp = "/view/quartos-visualizacao.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
}
