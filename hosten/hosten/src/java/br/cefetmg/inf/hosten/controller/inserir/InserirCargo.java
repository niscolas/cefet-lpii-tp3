package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class InserirCargo {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            
            String codCargo = request.getParameter("codCargo");
            String nomCargo = request.getParameter("nomCargo");
            boolean idtMaster = false;
            
            String [] codProgramas = request.getParameterValues("codPrograma");
            List<String> listaProgramas = null;
            for (String codPrograma : codProgramas) {
                listaProgramas.add(codPrograma);
            }
            
            Cargo cargo = new Cargo(codCargo, nomCargo, idtMaster);

            IManterCargo manterItem = new ManterCargoProxy();
            boolean testeRegistro = manterItem.inserir(cargo, listaProgramas);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Cargo inserido com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o cargo!");
            
            jsp = "/servletweb?acao=ListarCargos&tipoAcao=Padrao";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
