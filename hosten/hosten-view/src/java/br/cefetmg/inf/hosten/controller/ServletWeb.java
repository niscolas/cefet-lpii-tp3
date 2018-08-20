package br.cefetmg.inf.hosten.controller;

import br.cefetmg.inf.hosten.controller.listar.*;
import br.cefetmg.inf.hosten.controller.buscar.*;
import br.cefetmg.inf.hosten.controller.inserir.*;
import br.cefetmg.inf.hosten.controller.alterar.*;
import br.cefetmg.inf.hosten.controller.excluir.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletWeb", urlPatterns = {"/servletweb"})
public class ServletWeb extends HttpServlet {
    String jsp = "";
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        
        if (acao.equals("Login")) {
            jsp = Login.execute(request);
        } else if (acao.equals("Logout")) {
            jsp = Logout.execute(request);
        } else if (acao.equals("ListarItensConforto")) {
            jsp = ListarItensConforto.execute(request);
        } else if (acao.equals("InserirItemConforto")) {
            jsp = InserirItemConforto.execute(request);
        } else if (acao.equals("BuscarItemConforto")) {
            jsp = BuscarItemConforto.execute(request);
        } else if (acao.equals("AlterarItemConforto")) {
            jsp = AlterarItemConforto.execute(request);
        } else if (acao.equals("ExcluirItemConforto")) {
            jsp = ExcluirItemConforto.execute(request);
        }

        //Redirecionando pagina
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
