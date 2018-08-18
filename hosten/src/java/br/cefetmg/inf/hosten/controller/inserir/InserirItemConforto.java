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
            boolean testeRegistro = manterItem.inserir(itemConforto);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Item inserido com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o item!");
            
            jsp = "/servletweb?acao=ListarItensConforto";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
