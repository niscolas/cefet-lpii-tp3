package br.cefetmg.inf.hosten.controller;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import javax.servlet.http.HttpServletRequest;

public class Login {

    private static String email;
    private static String senha;
    
    @SuppressWarnings("static-access")
    public static String execute(HttpServletRequest request) {

        String jsp = "";

        try {
            email = request.getParameter("email");
            senha = request.getParameter("password");

            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            Usuario usr = manterUsuario.usuarioLogin(email, senha);

            if (usr == null) {
                String erro = "Usuário nao encontrado!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else {
                request.getSession().setAttribute("desEmail", usr.getDesEmail());
                request.getSession().setAttribute("codCargo", usr.getCodCargo());
                request.getSession().setAttribute("codUsuario", usr.getCodUsuario());
                request.getSession().setAttribute("nomUsuario", usr.getNomUsuario());
                jsp = "/servletweb?acao=ListarQuartosEstados";
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
}
