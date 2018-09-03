package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarCargos {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCargo manterCargo = new ManterCargoProxy();
            List<Cargo> listaCargos = manterCargo.listarTodos();
            
            request.setAttribute("listaCargos", listaCargos);
            
            // array com lista de programas
            // cada posição do array corresponde a uma lista de programas relacionados
            // ao cargo na mesma posição em listaCargos
            ArrayList arrayProgramasCargos = new ArrayList();
            for (Cargo cargo : listaCargos) {
                List<Programa> listaProgramas = manterCargo.listarProgramasRelacionados(cargo.getCodCargo());
                arrayProgramasCargos.add(listaProgramas);
            }
            request.setAttribute("arrayProgramasCargos", arrayProgramasCargos);
            
            jsp = "/view/cargos.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
