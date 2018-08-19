package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterUsuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;

public class ExcluirItemConforto {

    private static String email;
    private static String senha;
    
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        email = (String)request.getSession().getAttribute("email");
        senha = request.getParameter("senhaFuncionario");

        ManterUsuario manterUsuario = new ManterUsuario();
        Usuario usr = manterUsuario.usuarioLogin(email, senha);

        try {
            if (usr == null) {
                String erro = "Senha errada!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else {
                ManterItemConforto manterItem = new ManterItemConforto();
                String codItemExcluir = request.getParameter("codItem");

                List<ItemConforto> itemPesquisar = manterItem.listar(codItemExcluir, "codItem");
                if (!itemPesquisar.isEmpty()) {
                    ItemConforto itemConforto = itemPesquisar.get(0);
                    manterItem.alterar(codItemExcluir, itemConforto);
                    
                    jsp = "/view/itens-conforto.jsp";
                } else {
                    String erro = "Ocorreu erro ao excluir o item de conforto!";
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
