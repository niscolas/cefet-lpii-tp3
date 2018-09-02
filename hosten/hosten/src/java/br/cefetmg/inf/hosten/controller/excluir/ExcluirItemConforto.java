package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;

public class ExcluirItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
      
        try {  
            IManterItemConforto manterItem = new ManterItemConfortoProxy();
            String codItemExcluir = request.getParameter("codItem");

            List<ItemConforto> itemPesquisar = manterItem.listar(codItemExcluir, "codItem");
            if (!itemPesquisar.isEmpty()) {
                manterItem.excluir(codItemExcluir);
                jsp = "/servletweb?acao=ListarItensConforto";
            } else {
                String erro = "Ocorreu erro ao excluir o item de conforto!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        
        return jsp;
    }
    
}
