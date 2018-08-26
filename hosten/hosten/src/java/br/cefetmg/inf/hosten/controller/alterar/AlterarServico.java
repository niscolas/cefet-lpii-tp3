package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarServico {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterServico manterServico = new ManterServicoProxy();

            String codServicoEditar = request.getParameter("seqServico");

            List<Servico> servicoPesquisar = manterServico.listar(codServicoEditar, "seqServico");
            if (!servicoPesquisar.isEmpty()) {
                String desServico = request.getParameter("desServico");
                Double vlrUnit = Double.parseDouble(request.getParameter("vlrUnit"));
                String codServicoArea = request.getParameter("codServicoArea");

                Servico servico = new Servico(desServico, vlrUnit, codServicoArea);
                
                manterServico.alterar(codServicoEditar, servico);
                jsp = "/servletweb?acao=ListarServicos";
            } else {
                String erro = "Ocorreu erro ao alterar o servico!";
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
