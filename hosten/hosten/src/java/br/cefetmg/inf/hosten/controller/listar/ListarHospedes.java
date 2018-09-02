package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.proxy.ManterHospedeProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarHospedes {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterHospede manterHospede = new ManterHospedeProxy();
            List<Hospede> listaHospedes = manterHospede.listarTodos();

            request.setAttribute("listaHospedes", listaHospedes);
            jsp = "/view/hospedes.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
}
