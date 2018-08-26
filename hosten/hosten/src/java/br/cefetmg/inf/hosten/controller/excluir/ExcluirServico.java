package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterServicoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirServico {

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
                IManterServico manterServico = new ManterServicoProxy();
                String codServicoExcluir = request.getParameter("seqServico");

                List<Servico> servicoPesquisar = manterServico.listar(codServicoExcluir, "seqServico");
                if (!servicoPesquisar.isEmpty()) {
                    manterServico.excluir(codServicoExcluir);
                    
                    jsp = "/view/servicos.jsp";
                } else {
                    String erro = "Ocorreu erro ao excluir o serviço!";
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
