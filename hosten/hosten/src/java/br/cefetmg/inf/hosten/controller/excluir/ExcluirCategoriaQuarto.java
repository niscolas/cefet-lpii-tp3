package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirCategoriaQuarto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {
            IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
            String codCategoriaExcluir = request.getParameter("codCategoria");

            List<CategoriaQuarto> categoriaPesquisar = manterCategoria.listar(codCategoriaExcluir, "codCategoria");
            if (!categoriaPesquisar.isEmpty()) {
                manterCategoria.excluir(codCategoriaExcluir);
                jsp = "/servletweb?acao=ListarCategoriasQuarto";
            } else {
                String erro = "Ocorreu erro ao excluir o categoria de quarto!";
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
