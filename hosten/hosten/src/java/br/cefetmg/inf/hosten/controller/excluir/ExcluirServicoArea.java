package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirServicoArea {

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
                IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
                String codServicoAreaExcluir = request.getParameter("codServicoArea");

                List<ServicoArea> servicoAreaPesquisar = manterServicoArea.listar(codServicoAreaExcluir, "codServicoArea");
                if (!servicoAreaPesquisar.isEmpty()) {
                    manterServicoArea.excluir(codServicoAreaExcluir);
                    
                    jsp = "/view/servico-areas.jsp";
                } else {
                    String erro = "Ocorreu erro ao excluir a área de serviço!";
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
