package br.cefetmg.inf.hosten.controller.buscar;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class BuscarUsuario {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String codUsuario = request.getParameter("codUsuario");

        IManterUsuario manterUsuario = new ManterUsuarioProxy();

        try {
            List<Usuario> listaUsuarios = manterUsuario.listar(codUsuario, "codUsuario");

            if (!listaUsuarios.isEmpty()) {
                Usuario usuario = listaUsuarios.get(0);
                request.setAttribute("usuario", usuario);
            }

            jsp = "/view/funcionarios.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }

        return jsp;
    }

}
