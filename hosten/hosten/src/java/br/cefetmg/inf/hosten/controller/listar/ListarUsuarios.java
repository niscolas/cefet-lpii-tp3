package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarUsuarios {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            List<Usuario> listaUsuarios = manterUsuario.listarTodos();

            request.setAttribute("listaUsuarios", listaUsuarios);
            jsp = "/view/funcionarios.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
