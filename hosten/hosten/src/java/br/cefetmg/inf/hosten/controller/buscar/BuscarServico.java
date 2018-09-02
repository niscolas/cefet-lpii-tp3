package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarServico {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String seqServico = request.getParameter("seqServico");
        
        IManterServico manterServico = new ManterServicoProxy();
        
        try {
            List<Servico> listaItens = manterServico.listar(seqServico, "seqServico");
            
            if (!listaItens.isEmpty()) {
                Servico servico = listaItens.get(0);
                request.setAttribute("servico", servico);
               
                IManterServicoArea manterArea = new ManterServicoAreaProxy();
                List<ServicoArea> listaServicoAreas = manterArea.listar(servico.getCodServicoArea(), "codServicoArea");
                request.setAttribute("categoriaQuarto", listaServicoAreas.get(0));
            }
            
            if (tipoAcao.equals("Alterar")) {
                IManterServicoArea manterArea = new ManterServicoAreaProxy();
                List<ServicoArea> listaServicoAreas = manterArea.listarTodos();
                request.setAttribute("listaServicoAreas", listaServicoAreas);
                
                jsp = "/view/servicos-alterar.jsp";

            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/servicos-excluir.jsp";
            }
            
//            jsp = "/view/servicos.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
