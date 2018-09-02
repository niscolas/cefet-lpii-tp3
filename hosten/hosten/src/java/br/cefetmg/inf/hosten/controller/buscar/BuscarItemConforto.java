package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class BuscarItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String codItem = request.getParameter("codItem");
        
        IManterItemConforto manterItem = new ManterItemConfortoProxy();
        
        try {
            List<ItemConforto> listaItens = manterItem.listar(codItem, "codItem");
            
            if (!listaItens.isEmpty()) {
                ItemConforto item = listaItens.get(0);
                request.setAttribute("itemConforto", item);
            }
            
            if (tipoAcao.equals("Alterar")) {
                jsp = "/view/itens-conforto-alterar.jsp";
            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/itens-conforto-excluir.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
