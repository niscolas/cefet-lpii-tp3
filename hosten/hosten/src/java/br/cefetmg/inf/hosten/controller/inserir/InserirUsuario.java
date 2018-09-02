package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import javax.servlet.http.HttpServletRequest;

public class InserirUsuario {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            String codUsuario = request.getParameter("codUsuario");
            String nomUsuario = request.getParameter("nomUsuario");
            String codCargo = request.getParameter("codCargo");
            String desSenha = request.getParameter("desSenha");
            String desEmail = request.getParameter("desEmail");

            Usuario usuario = new Usuario(codUsuario, nomUsuario, codCargo, desSenha, desEmail);

            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            boolean testeRegistro = manterUsuario.inserir(usuario);

            if (testeRegistro) {
                request.setAttribute("mensagem", "Usuário inserido com sucesso");
            } else {
                request.setAttribute("mensagem", "Não foi possível inserir o usuário!");
            }

            jsp = "/servletweb?acao=ListarUsuarios";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }

}
