package br.cefetmg.inf.hosten.controller.excluir;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import br.cefetmg.inf.hosten.proxy.ManterUsuarioProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ExcluirCargo {

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
                IManterCargo manterCargo = new ManterCargoProxy();
                
                HttpSession session = request.getSession();
                String codCargo = (String)session.getAttribute("codCargo");
                
                String codCargoExcluir = request.getParameter("codCargo");
                
                if (!codCargo.equals(codCargoExcluir)) {
                    List<Cargo> cargoPesquisar = manterCargo.listar(codCargoExcluir, "codCargo");
                    if (!cargoPesquisar.isEmpty()) {
                        manterCargo.excluir(codCargoExcluir);

                        jsp = "/view/cargos.jsp";
                    } else {
                        String erro = "Ocorreu erro ao excluir o cargo!";
                        request.setAttribute("erro", erro);
                        jsp = "/erro.jsp";
                    }
                } else {
                    String erro = "O cargo a excluir é o seu!";
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
