package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import javax.servlet.http.HttpServletRequest;

public class InserirQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            int nroQuarto = Integer.parseInt(request.getParameter("nroQuarto"));
            String codCategoria = request.getParameter("codCategoria");
            boolean idtOcupado = false;
            
            Quarto quarto = new Quarto(nroQuarto, codCategoria, idtOcupado);

            IManterQuarto manterQuarto = new ManterQuartoProxy();
            boolean testeRegistro = manterQuarto.inserir(quarto);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Quarto inserido com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o quarto!");
            
            jsp = "/servletweb?acao=ListarQuartos";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
