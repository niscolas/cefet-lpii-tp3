package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarQuartos {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            List<Quarto> listaQuartos = manterQuarto.listarTodos();
            
            List<String> listaNomCategorias = null;
            IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
            for (Quarto quarto : listaQuartos) {
                List<CategoriaQuarto> listaCategorias = manterCategoria.listar(quarto.getCodCategoria(), "codCategoria");
                listaNomCategorias.add(listaCategorias.get(0).getNomCategoria());
            }

            request.setAttribute("listaQuartos", listaQuartos);
            request.setAttribute("listaNomCategorias", listaNomCategorias);
            jsp = "/view/quartos-visualizacao.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
