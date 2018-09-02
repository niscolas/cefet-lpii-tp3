package br.cefetmg.inf.hosten.controller.hospedagem;

/*
    Confirma o check-in
    Direciona para a página de estado dos quartos (ListarEstadosQuartos)
*/

import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
import javax.servlet.http.HttpServletRequest;

public class ConfirmarCheckIn {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String cpfHospede = request.getParameter("codCPF");
            String nroQuarto = request.getParameter("nroQuarto");
            int diasDeEstadia = Integer.parseInt(request.getParameter("diasDeEstadia"));
            int nroAdultos = Integer.parseInt(request.getParameter("nroAdultos"));
            int nroCriancas = Integer.parseInt(request.getParameter("nroCriancas"));
            
            IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();
            controlarHosp.efetuarCheckIn(nroQuarto, cpfHospede, diasDeEstadia, nroAdultos, nroCriancas);
            
            jsp = "/servletweb?acao=ListarQuartosEstados";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
