package br.cefetmg.inf.hosten.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login {

    @SuppressWarnings("static-access")
    public static String execute(HttpServletRequest request) {

        String jsp = "";

//        try {
//            String nome = request.getParameter("nome");
//            String senha = request.getParameter("senha");
//
//            IManterUsuario manterUsuario = new ManterUsuario();
//            Usuario usr = manterUsuario.getUserLogin(nome, senha);
//
//            if (usr == null) {
//                String erro = "Usuario nao encontrado!";
//                request.setAttribute("erro", erro);
//                jsp = "/erro.jsp";
//            } else {
//                request.getSession().setAttribute("codUsuario", usr.getId());
//                jsp = "/menu.jsp";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsp = "";
//        }
        return jsp;
    }

    public static void validarSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codUsuario = (Long) request.getSession().getAttribute("codUsuario");
        String jsp = "";
        if (codUsuario == null) {
            jsp = "/index.jsp";
            //Redirecionando pagina
            RequestDispatcher rd = request.getRequestDispatcher(jsp);
            rd.forward(request, response);
        }
    }
    
}
