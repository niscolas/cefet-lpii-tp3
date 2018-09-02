package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;

public class BuscarCategoriaQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String codCategoria = request.getParameter("codCategoria");
        
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        
        try {
            List<CategoriaQuarto> listaCategorias = manterCategoria.listar(codCategoria, "codCategoria");
            
            if (!listaCategorias.isEmpty()) {
                CategoriaQuarto categoria = listaCategorias.get(0);
                request.setAttribute("categoriaQuarto", categoria);
            }
            
            jsp = "/view/quartos-categorias.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
