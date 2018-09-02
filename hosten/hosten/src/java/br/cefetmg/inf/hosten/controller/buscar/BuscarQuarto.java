package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarQuarto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String nroQuarto = request.getParameter("nroQuarto");
        
        IManterQuarto manterQuarto = new ManterQuartoProxy();
        
        try {
            List<Quarto> listaQuartos = manterQuarto.listar(nroQuarto, "nroQuarto");
            
            if (!listaQuartos.isEmpty()) {
                Quarto quarto = listaQuartos.get(0);
                request.setAttribute("quarto", quarto);

                IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
                List<CategoriaQuarto> listaCategorias = manterCategoria.listar(quarto.getCodCategoria(), "codCategoria");
                request.setAttribute("categoriaQuarto", listaCategorias.get(0));
            }
            
            if (tipoAcao.equals("Alterar")) {
                IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
                List<CategoriaQuarto> listaCategorias = manterCategoria.listarTodos();
                request.setAttribute("listaCategorias", listaCategorias);
                
                jsp = "/view/quartos-alterar.jsp";

            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/quartos-excluir.jsp";
            }
//            jsp = "/view/quartos-visualizacao.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
}
