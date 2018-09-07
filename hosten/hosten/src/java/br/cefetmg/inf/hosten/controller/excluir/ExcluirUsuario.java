package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ExcluirUsuario {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            String codUsuarioExcluir = request.getParameter("codUsuario");
            
            HttpSession session = request.getSession();
            String codUsuario = (String)session.getAttribute("codUsuario");
                
            if (!codUsuario.equals(codUsuarioExcluir)) {
                List<Usuario> usuarioPesquisar = manterUsuario.listar(codUsuarioExcluir, "codUsuario");
                if (!usuarioPesquisar.isEmpty()) {
                    manterUsuario.excluir(codUsuarioExcluir);
                    jsp = "/servletweb?acao=ListarUsuarios";
                } else {
                    String erro = "Ocorreu erro ao excluir o usuário!";
                    request.setAttribute("erro", erro);
                    jsp = "/erro.jsp";
                }
            } else {
                String erro = "Você não pode se excluir do sistema!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "erro.jsp";
        }
        
        return jsp;
    }
    
}
