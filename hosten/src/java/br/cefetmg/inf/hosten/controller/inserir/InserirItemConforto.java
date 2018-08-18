package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.ManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConfortoImpl;
import javax.servlet.http.HttpServletRequest;

public class InserirItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            ManterItemConforto manterItem = new ManterItemConfortoImpl();
            
            String codItem = request.getParameter("codItem");
            String desItem = request.getParameter("desItem");

            ItemConforto itemConforto = new ItemConforto(codItem, desItem);
            manterItem.inserir(itemConforto);
            
            jsp = "/view/itens-conforto.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
