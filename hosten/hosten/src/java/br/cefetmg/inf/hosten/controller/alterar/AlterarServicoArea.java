package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarServicoArea {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();

            String codServicoAreaEditar = request.getParameter("codServicoAreaAntigo");

            List<ServicoArea> servicoAreaPesquisar = manterServicoArea.listar(codServicoAreaEditar, "codServicoArea");
            if (!servicoAreaPesquisar.isEmpty()) {
                String codServicoArea = request.getParameter("codServicoArea");
                String nomServicoArea = request.getParameter("nomServicoArea");

                ServicoArea servicoArea = new ServicoArea(codServicoArea, nomServicoArea);
                manterServicoArea.alterar(codServicoAreaEditar, servicoArea);
                jsp = "/servletweb?acao=ListarServicoAreas";
            } else {
                String erro = "Ocorreu erro ao alterar a área de serviço!";
                request.setAttribute("mensagem", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
