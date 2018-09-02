package br.cefetmg.inf.hosten.controller.exibirtela;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.ManterItemConfortoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExibirTelaInserirCategoriaQuarto {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterItemConforto manterItem = new ManterItemConfortoProxy();
            List<ItemConforto> listaItens = manterItem.listarTodos();
            
            request.setAttribute("listaItens", listaItens);
            
            jsp = "/view/quartos-categorias-inserir.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
