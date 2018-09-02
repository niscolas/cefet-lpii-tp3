package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class BuscarCategoriaQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String codCategoria = request.getParameter("codCategoria");
        
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        
        try {
            List<CategoriaQuarto> listaCategorias = manterCategoria.listar(codCategoria, "codCategoria");
            
            if (!listaCategorias.isEmpty()) {
                CategoriaQuarto categoria = listaCategorias.get(0);
                request.setAttribute("categoriaQuarto", categoria);

                // pegar a lista dos itens associados àquela categoria
                List<ItemConforto> listaItensCategoria = null;
                //
                listaItensCategoria = manterCategoria.listarItensRelacionados(codCategoria);
                request.setAttribute("listaItensCategoria", listaItensCategoria);
            }
            
            if (tipoAcao.equals("Alterar")) {
                IManterItemConforto manterItem = new ManterItemConfortoProxy();
                List<ItemConforto> listaItens = manterItem.listarTodos();
                request.setAttribute("listaItens", listaItens);
                
                jsp = "/view/quartos-categorias-alterar.jsp";

            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/quartos-categorias-excluir.jsp";
            }
//            jsp = "/view/quartos-categorias.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
