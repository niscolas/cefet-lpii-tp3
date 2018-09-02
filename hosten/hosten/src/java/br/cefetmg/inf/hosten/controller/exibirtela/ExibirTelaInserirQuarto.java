package br.cefetmg.inf.hosten.controller.exibirtela;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExibirTelaInserirQuarto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
            List<CategoriaQuarto> listaCategorias = manterCategoria.listarTodos();

            request.setAttribute("listaCategorias", listaCategorias);
            
            jsp = "/view/quartos-inserir.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
