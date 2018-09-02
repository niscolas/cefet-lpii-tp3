package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarCategoriaQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
            
            String codCategoriaEditar = request.getParameter("codCategoriaAntiga");
            List<CategoriaQuarto> categoriaPesquisar = manterCategoria.listar(codCategoriaEditar, "codCategoria");

            if (!categoriaPesquisar.isEmpty()) {
                String codCategoria = request.getParameter("codCategoria");
                String nomCategoria = request.getParameter("nomCategoria");
                Double vlrDiaria = Double.parseDouble(request.getParameter("vlrDiaria"));
                String [] codItensSelecionados = request.getParameterValues("codItem");
                
                IManterItemConforto manterItem = new ManterItemConfortoProxy();
                List<ItemConforto> listaItens = null;
                for (String codItem : codItensSelecionados) {
                    List<ItemConforto> resultadoBusca = manterItem.listar(codItem, "codItem");
                    listaItens.add(resultadoBusca.get(0));
                }

                CategoriaQuarto categoria = new CategoriaQuarto(codCategoria, nomCategoria, vlrDiaria);
                manterCategoria.alterar(codCategoriaEditar, categoria, listaItens);
                jsp = "/servletweb?acao=ListarCategoriasQuarto";
//                jsp = "/view/quartos-categorias.jsp";
            } else {
                String erro = "Ocorreu erro ao alterar a categoria de quarto!";
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
