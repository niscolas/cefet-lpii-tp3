package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class LancarDespesa {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServico manterServico = new ManterServicoProxy();
            List<Servico> listaServicos = manterServico.listarTodos();
            
            List<String> listaServicoAreasNomes = null;
            for (Servico servico : listaServicos) {
                IManterServicoArea manterArea = new ManterServicoAreaProxy();
                List<ServicoArea> area = manterArea.listar("codServicoArea", servico.getCodServicoArea());
                listaServicoAreasNomes.add(area.get(0).getNomServicoArea());
            }
            
            request.setAttribute("listaServicos", listaServicos);
            request.setAttribute("listaServicoAreasNomes", listaServicoAreasNomes);
            
            jsp = "/view/despesas.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
