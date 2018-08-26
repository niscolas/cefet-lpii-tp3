package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import javax.servlet.http.HttpServletRequest;

public class InserirServico {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String desServico = request.getParameter("desServico");
            Double vlrUnit = Double.parseDouble(request.getParameter("vlrUnit"));
            String codServicoArea = request.getParameter("codServicoArea");
            
            Servico servico = new Servico(desServico, vlrUnit, codServicoArea);
            
            IManterServico manterServico = new ManterServicoProxy();
            boolean testeRegistro = manterServico.inserir(servico);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Serviço inserido com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o serviço!");
            
            jsp = "/servletweb?acao=ListarServicos";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
