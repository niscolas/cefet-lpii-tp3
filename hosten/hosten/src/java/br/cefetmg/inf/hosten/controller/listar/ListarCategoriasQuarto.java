package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarCategoriasQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
            List<CategoriaQuarto> listaCategorias = manterCategoria.listarTodos();

            request.setAttribute("listaCategorias", listaCategorias);

            // array com lista de itens de conforto
            // cada posição do array corresponde a uma lista de itens relacionados
            // à categoria na mesma posição em listaCategorias
            ArrayList arrayItensCategoria = new ArrayList();
            for (CategoriaQuarto categoria : listaCategorias) {
                List<ItemConforto> listaItens = manterCategoria.listarItensRelacionados(categoria.getCodCategoria());
                arrayItensCategoria.add(listaItens);
            }
            request.setAttribute("arrayItensCategoria", arrayItensCategoria);
            

            jsp = "/view/itens-conforto.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
