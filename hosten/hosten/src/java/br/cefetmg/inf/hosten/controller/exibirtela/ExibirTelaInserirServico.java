package br.cefetmg.inf.hosten.controller.exibirtela;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExibirTelaInserirServico {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
            List<ServicoArea> listaServicoAreas = manterServicoArea.listarTodos();
            
            request.setAttribute("listaServicoAreas", listaServicoAreas);
            
            jsp = "/view/servicos-inserir.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
