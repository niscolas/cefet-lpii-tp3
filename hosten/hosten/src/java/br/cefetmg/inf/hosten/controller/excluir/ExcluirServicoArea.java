package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirServicoArea {
    
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {           
            IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
            String codServicoAreaExcluir = request.getParameter("codServicoArea");

            List<ServicoArea> servicoAreaPesquisar = manterServicoArea.listar(codServicoAreaExcluir, "codServicoArea");
            if (!servicoAreaPesquisar.isEmpty()) {
                manterServicoArea.excluir(codServicoAreaExcluir);
                jsp = "/hosten/servletweb?acao=ListarServicoAreas&tipoAcao=Padrao";
            } else {
                String erro = "Ocorreu erro ao excluir a área de serviço!";
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
