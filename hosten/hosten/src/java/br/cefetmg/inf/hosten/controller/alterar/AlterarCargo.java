package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AlterarCargo {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterCargo manterCargo = new ManterCargoProxy();
            
            String codCargoEditar = request.getParameter("codCargoAntigo");
            
            List<Cargo> cargoPesquisar = manterCargo.listar(codCargoEditar, "codCargo");
            if (!cargoPesquisar.isEmpty()) {
                String codCargo = request.getParameter("codCargo");
                String nomCargo = request.getParameter("nomCargo");
                boolean idtMaster = false;

                String [] codProgramas = request.getParameterValues("codPrograma");
                List<String> listaProgramas = null;
                for (String codPrograma : codProgramas) {
                    listaProgramas.add(codPrograma);
                }

                Cargo cargo = new Cargo(codCargo, nomCargo, idtMaster);

                manterCargo.alterar(codCargoEditar, cargo, listaProgramas);
                jsp = "/servletweb?acao=ListarCargos&tipoAcao=Padrao";
            } else {
                String erro = "Ocorreu erro ao alterar o cargo!";
                request.setAttribute("mensagem", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
