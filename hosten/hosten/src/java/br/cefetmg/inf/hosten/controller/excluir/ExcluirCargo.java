package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ExcluirCargo {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {            
            IManterCargo manterCargo = new ManterCargoProxy();
            HttpSession session = request.getSession();
            String codCargo = (String)session.getAttribute("codCargo");
            String codCargoExcluir = request.getParameter("codCargo");

            if (!codCargo.equals(codCargoExcluir)) {
                List<Cargo> cargoPesquisar = manterCargo.listar(codCargoExcluir, "codCargo");
                if (!cargoPesquisar.isEmpty()) {
                    manterCargo.excluir(codCargoExcluir);
                    jsp = "/view/cargos.jsp";
                } else {
                    String erro = "Ocorreu erro ao excluir o cargo!";
                    request.setAttribute("erro", erro);
                    jsp = "/erro.jsp";
                }
            } else {
                String erro = "O cargo a excluir é o seu!";
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
