package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarUsuario {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        String codUsuario = request.getParameter("codUsuario");

        IManterUsuario manterUsuario = new ManterUsuarioProxy();

        try {
            List<Usuario> listaUsuarios = manterUsuario.listar(codUsuario, "codUsuario");

            if (!listaUsuarios.isEmpty()) {
                Usuario usuario = listaUsuarios.get(0);
                request.setAttribute("usuario", usuario);

                IManterCargo manterCargo = new ManterCargoProxy();
                List<Cargo> listaCargos = manterCargo.listar(usuario.getCodCargo(), "codCargo");
                request.setAttribute("cargo", listaCargos.get(0));
            }

            if (tipoAcao.equals("Alterar")) {
                IManterCargo manterCargo = new ManterCargoProxy();
                List<Cargo> listaCargos = manterCargo.listarTodos();
                request.setAttribute("listaCategorias", listaCargos);
                
                jsp = "/view/funcionarios-alterar.jsp";

            } else if (tipoAcao.equals("Excluir")) {
                jsp = "/servletweb?acao=ListarCargos&tipoAcao=TabelaUsuarioExcluir";
            }
//            jsp = "/view/funcionarios.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }

        return jsp;
    }

}
