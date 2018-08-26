package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ExcluirQuarto {

    private static String email;
    private static String senha;

    public static String execute(HttpServletRequest request) {
        String jsp = "";

        email = (String) request.getSession().getAttribute("email");
        senha = request.getParameter("senhaFuncionario");

        try {
            IManterUsuario manterUsuario = new ManterUsuarioProxy();
            Usuario usr = manterUsuario.usuarioLogin(email, senha);
            if (usr == null) {
                String erro = "Senha errada!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else {
                IManterQuarto manterQuarto = new ManterQuartoProxy();
                String nroQuartoExcluir = request.getParameter("nroQuarto");

                List<Quarto> quartoPesquisar = manterQuarto.listar(nroQuartoExcluir, "nroQuarto");
                if (!quartoPesquisar.isEmpty()) {
                    manterQuarto.excluir(nroQuartoExcluir);
                    jsp = "/servletweb?acao=ListarQuartos";
                } else {
                    String erro = "Ocorreu erro ao excluir o quarto!";
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
