package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarServicos {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServico manterServico = new ManterServicoProxy();
            List<Servico> listaServicos = manterServico.listarTodos();
            
            // Lista de áreas de serviço
            // cada posição corresponde à área do serviço na mesma posição de listaServicos
            List<ServicoArea> listaServicoAreas = null;
            IManterServicoArea manterArea = new ManterServicoAreaProxy();
            for (Servico servico : listaServicos) {
                List<ServicoArea> listaAreasBuscadas = manterArea.listar(servico.getCodServicoArea(), "codServicoArea");
                listaServicoAreas.add(listaAreasBuscadas.get(0));
            }

            request.setAttribute("listaServicos", listaServicos);
            request.setAttribute("listaServicoAreas", listaServicoAreas);
            
            jsp = "/view/servicos.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
