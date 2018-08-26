package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AlterarUsuario {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            
            String codUsuarioEditar = request.getParameter("codUsuarioAntigo");
            
            HttpSession session = request.getSession();
            if (session.getAttribute("codUsuario").equals(codUsuarioEditar)) {
                String erro = "Não é possível editar as próprias informações!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else {
                List<Usuario> usuarioPesquisar = manterUsuario.listar(codUsuarioEditar, "codUsuario");
                if (!usuarioPesquisar.isEmpty()) {
                    String codUsuario = request.getParameter("codUsuario");
                    String nomUsuario = request.getParameter("nomUsuario");
                    String codCargo = request.getParameter("codCargo");
                    String desSenha = request.getParameter("desSenha");
                    String desEmail = request.getParameter("desEmail");
                    
                    Usuario usuario = new Usuario(codUsuario, nomUsuario, codCargo, desSenha, desEmail);

                    manterUsuario.alterar(codUsuarioEditar, usuario);
                    jsp = "/servletweb?acao=ListarUsuarios";
                } else {
                    String erro = "Ocorreu erro ao alterar as informações do usuário!";
                    request.setAttribute("erro", erro);
                    jsp = "/erro.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
