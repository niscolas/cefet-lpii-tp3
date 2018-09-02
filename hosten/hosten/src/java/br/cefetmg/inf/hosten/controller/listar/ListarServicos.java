package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarServicos {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServico manterServico = new ManterServicoProxy();
            List<Servico> listaServicos = manterServico.listarTodos();
            
            request.setAttribute("listaServicos", listaServicos);
            jsp = "/view/servicos.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
