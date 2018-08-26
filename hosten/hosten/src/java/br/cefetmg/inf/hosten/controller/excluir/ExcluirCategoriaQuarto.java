package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirCategoriaQuarto {

    private static String email;
    private static String senha;
    
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        email = (String)request.getSession().getAttribute("email");
        senha = request.getParameter("senhaFuncionario");


        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            Usuario usr = manterUsuario.usuarioLogin(email, senha);
            if (usr == null) {
                String erro = "Senha errada!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else {
                IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
                String codCategoriaExcluir = request.getParameter("codCategoria");

                List<CategoriaQuarto> categoriaPesquisar = manterCategoria.listar(codCategoriaExcluir, "codCategoria");
                if (!categoriaPesquisar.isEmpty()) {
                    manterCategoria.excluir(codCategoriaExcluir);
                    
                    jsp = "/view/quartos-categorias.jsp";
                } else {
                    String erro = "Ocorreu erro ao excluir o categoria de quarto!";
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
