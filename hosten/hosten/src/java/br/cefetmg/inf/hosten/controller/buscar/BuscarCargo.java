package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarCargo {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String codCargo = request.getParameter("codCargo");
        
        IManterCargo manterCargo = new ManterCargoProxy();
        
        try {
            List<Cargo> listaCargos = manterCargo.listar(codCargo, "codCargo");
                        
            if (!listaCargos.isEmpty()) {
                Cargo cargo = listaCargos.get(0);
                request.setAttribute("cargo", cargo);

                // pegar a lista dos programas associados àquele cargo
                List<Programa> listaProgramasCargo = null;
                //
                // todo
                //
                request.setAttribute("listaProgramasCargo", listaProgramasCargo);
            }
            
            if (tipoAcao.equals("Alterar")) {
//                IManterPrograma manterPrograma = new ManterProgramaProxy();
//                List<Programa> listaProgramas = manterPrograma.listarTodos();
//                request.setAttribute("listaProgramas", listaProgramas);
                
                jsp = "/view/cargos-alterar.jsp";

            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/view/cargos-excluir.jsp";
            }
//            jsp = "/view/cargos.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
