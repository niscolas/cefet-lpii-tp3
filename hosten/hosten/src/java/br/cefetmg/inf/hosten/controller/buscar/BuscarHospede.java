package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.proxy.ManterHospedeProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarHospede {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String codCPF = request.getParameter("codCPF");

        IManterHospede manterHospede = new ManterHospedeProxy();

        try {
            List<Hospede> listaHospedes = manterHospede.listar(codCPF, "codCPF");

            if (!listaHospedes.isEmpty()) {
                Hospede hospede = listaHospedes.get(0);
                request.setAttribute("Hospede", hospede);
            }

            jsp = "/view/hospedes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }

        return jsp;
    }

}
