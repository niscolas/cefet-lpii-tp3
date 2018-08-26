package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class BuscarItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String codItem = request.getParameter("codItem");
        
        IManterItemConforto manterItem = new ManterItemConfortoProxy();
        
        try {
            List<ItemConforto> listaItens = manterItem.listar(codItem, "codItem");
            
            if (!listaItens.isEmpty()) {
                ItemConforto item = listaItens.get(0);
                request.setAttribute("itemConforto", item);
            }
            
            jsp = "/view/itens-conforto.jsp#modal-edit-item";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        
        return jsp;
    }
    
}