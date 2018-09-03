package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class ListarItensConforto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");

        try {
            IManterItemConforto manterItem = new ManterItemConfortoProxy();
            List<ItemConforto> listaItens = manterItem.listarTodos();
            
            request.setAttribute("listaItens", listaItens);
            
            if (tipoAcao.equals("Padrao")) {
                jsp = "/view/itens-conforto.jsp";
            } else if (tipoAcao.equals("SelectCategoriaQuarto")) {
                jsp = "/view/quartos-categorias-inserir.jsp";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
