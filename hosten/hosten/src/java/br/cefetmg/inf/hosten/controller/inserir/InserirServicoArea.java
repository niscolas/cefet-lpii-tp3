package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import javax.servlet.http.HttpServletRequest;

public class InserirServicoArea {
    
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {
            String codServicoArea = request.getParameter("codServicoArea");
            String nomServicoArea = request.getParameter("nomServicoArea");
            
            ServicoArea servicoArea = new ServicoArea(codServicoArea, nomServicoArea);
            
            IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
            boolean testeRegistro = manterServicoArea.inserir(servicoArea);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Área de serviço inserida com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir a área de serviço!");
            
            jsp = "/servletweb?acao=ListarServicoAreas&tipoAcao=Padrao";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
