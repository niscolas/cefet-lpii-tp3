package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class ListarItensConforto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterItemConforto manterItem = new ManterItemConfortoProxy();
            List<ItemConforto> listaItens = manterItem.listarTodos();
            
            request.setAttribute("listaItens", listaItens);
            jsp = "/view/itens-conforto.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
