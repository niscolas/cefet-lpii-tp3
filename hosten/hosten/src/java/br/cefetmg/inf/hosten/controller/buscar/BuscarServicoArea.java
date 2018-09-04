package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarServicoArea {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String codServicoArea = request.getParameter("codServicoArea");

        IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();

        try {
            List<ServicoArea> listaItens = manterServicoArea.listar(codServicoArea, "codServicoArea");

            if (!listaItens.isEmpty()) {
                ServicoArea servicoArea = listaItens.get(0);
                request.setAttribute("servicoArea", servicoArea);
            }
            
            if (tipoAcao.equals("Alterar")) {
                jsp = "/view/servicos-areas-alterar.jsp";
            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/servicos-areas-excluir.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }

        return jsp;
    }

}
