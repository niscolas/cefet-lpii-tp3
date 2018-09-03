package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarUsuarios {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            List<Usuario> listaUsuarios = manterUsuario.listarTodos();

            // Lista de cargos
            // cada posição corresponde ao cargo do usuário na mesma posição de listaUsuarios
            List<Cargo> listaCargos = null;
            IManterCargo manterCargo = new ManterCargoProxy();
            for (Usuario usuario : listaUsuarios) {
                List<Cargo> listaCargosBuscados = manterCargo.listar(usuario.getCodCargo(), "codCargo");
                listaCargos.add(listaCargosBuscados.get(0));
            }

            request.setAttribute("listaUsuarios", listaUsuarios);
            request.setAttribute("listaCargos", listaCargos);
            
            jsp = "/view/funcionarios.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
