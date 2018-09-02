package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarServico {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String seqServico = request.getParameter("seqServico");
        
        IManterServico manterServico = new ManterServicoProxy();
        
        try {
            List<Servico> listaItens = manterServico.listar(seqServico, "seqServico");
            
            if (!listaItens.isEmpty()) {
                Servico servico = listaItens.get(0);
                request.setAttribute("servico", servico);
            }
            
            jsp = "/view/servicos.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
