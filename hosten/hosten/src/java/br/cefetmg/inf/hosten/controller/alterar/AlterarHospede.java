package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.proxy.ManterHospedeProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarHospede {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterHospede manterHospede = new ManterHospedeProxy();

            String codHospedeEditar = request.getParameter("codCPFAntigo");

            List<Hospede> hospedePesquisar = manterHospede.listar(codHospedeEditar, "codCPF");
            if (!hospedePesquisar.isEmpty()) {
                String codCPF = request.getParameter("codCPF");
                String nomHospede = request.getParameter("nomHospede");
                String desTelefone = request.getParameter("desTelefone");
                String desEmail = request.getParameter("desEmail");
                
                Hospede hospede = new Hospede(codCPF, nomHospede, desTelefone, desEmail);
                manterHospede.alterar(codHospedeEditar, hospede);
                jsp = "/servletweb?acao=ListarHospedes";
            } else {
                String erro = "Ocorreu erro ao alterar as informações do hóspede!";
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
