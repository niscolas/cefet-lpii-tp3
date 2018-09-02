package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarQuarto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            
            String nroQuartoEditar = request.getParameter("nroQuartoAntigo");
            
            List<Quarto> quartoPesquisar = manterQuarto.listar(nroQuartoEditar, "nroQuarto");
            
            if (!quartoPesquisar.isEmpty()) {
                int nroQuarto = Integer.parseInt(request.getParameter("nroQuarto"));
                String codCategoria = request.getParameter("codCategoria");
                boolean idtOcupado = false;

                Quarto quarto = new Quarto(nroQuarto, codCategoria, idtOcupado);
                manterQuarto.alterar(nroQuartoEditar, quarto);
    
                jsp = "/servletweb?acao=ListarQuartos";
            } else {
                String erro = "Ocorreu erro ao alterar o quarto!";
                request.setAttribute("mensagem", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
