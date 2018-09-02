package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.proxy.ManterHospedeProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/*
    Lista os hóspedes para exibir na página de Check-in
    Salva o número do quarto na requisição
    Direciona para a página de Check-in
*/

public class CheckIn {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterHospede manterHospede = new ManterHospedeProxy();
            List<Hospede> listaHospedes = manterHospede.listarTodos();
            
            int nroQuarto = Integer.parseInt(request.getParameter("nroQuarto"));
            System.out.println("numero quarto onde será efetuado o checkin: " + nroQuarto);
            
            request.setAttribute("nroQuarto", nroQuarto);
            request.setAttribute("listaHospedes", listaHospedes);
            
            jsp = "/view/checkin.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
}
