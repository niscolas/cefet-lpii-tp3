package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.proxy.ManterHospedeProxy;
import javax.servlet.http.HttpServletRequest;

public class InserirHospede {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {

            String codCPF = request.getParameter("codCPF");
            String nomHospede = request.getParameter("nomHospede");
            String desTelefone = request.getParameter("desTelefone");
            String desEmail = request.getParameter("desEmail");

            Hospede hospede = new Hospede(codCPF, nomHospede, desTelefone, desEmail);

            IManterHospede manterHospede = new ManterHospedeProxy();
            boolean testeRegistro = manterHospede.inserir(hospede);

            if (testeRegistro) {
                request.setAttribute("mensagem", "Hóspede inserido com sucesso");
            } else {
                request.setAttribute("mensagem", "Não foi possível inserir o hóspede!");
            }

            jsp = "/servletweb?acao=ListarHospedes";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
