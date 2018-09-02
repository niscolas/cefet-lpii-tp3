package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarQuartosEstados {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IControlarHospedagem controleHosp = new ControlarHospedagemProxy();
            List<QuartoEstado> listaQuartosEstados = controleHosp.listarTodos();

            request.setAttribute("listaQuartosEstados", listaQuartosEstados);
            jsp = "/view/quartos-estados.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
