package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarCargo {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String codCargo = request.getParameter("codCargo");
        
        IManterCargo manterCargo = new ManterCargoProxy();
        
        try {
            List<Cargo> listaCargos = manterCargo.listar(codCargo, "codCargo");
                        
            if (!listaCargos.isEmpty()) {
                Cargo cargo = listaCargos.get(0);
                request.setAttribute("cargo", cargo);
            }
            
            jsp = "/view/cargos.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        
        return jsp;
    }
    
}
