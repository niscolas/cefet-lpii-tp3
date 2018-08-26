package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarQuartos {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            List<Quarto> listaQuartos = manterQuarto.listarTodos();

            request.setAttribute("listaQuartos", listaQuartos);
            jsp = "/view/quartos-visualizacao.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

}
