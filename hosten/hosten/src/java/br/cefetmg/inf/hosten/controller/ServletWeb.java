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
        
        request.removeAttribute("acao");
        
        if (acao.equals("Login")) {
            jsp = Login.execute(request);
        } else if (acao.equals("Logout")) {
            jsp = Logout.execute(request);
        } 
        
        // Item de Conforto
        else if (acao.equals("ListarItensConforto")) {
            jsp = ListarItensConforto.execute(request);
        } else if (acao.equals("InserirItemConforto")) {
            jsp = InserirItemConforto.execute(request);
        } else if (acao.equals("BuscarItemConforto")) {
            jsp = BuscarCategoriaQuarto.execute(request);
        } else if (acao.equals("AlterarItemConforto")) {
            jsp = AlterarItemConforto.execute(request);
        } else if (acao.equals("ExcluirItemConforto")) {
            jsp = ExcluirItemConforto.execute(request);
        }

        // Categoria de Quartos
        else if (acao.equals("ListarCategoriasQuarto")) {
            jsp = ListarCategoriasQuarto.execute(request);
        } else if (acao.equals("InserirCategoriaQuarto")) {
            jsp = InserirCategoriaQuarto.execute(request);
        } else if (acao.equals("BuscarCategoriaQuarto")) {
            jsp = BuscarCategoriaQuarto.execute(request);
        } else if (acao.equals("AlterarCategoriaQuarto")) {
            jsp = AlterarCategoriaQuarto.execute(request);
        } else if (acao.equals("ExcluirCategoriasQuarto")) {
            jsp = ExcluirCategoriaQuarto.execute(request);
        }

        // Quartos
        else if (acao.equals("ListarQuartos")) {
            jsp = ListarQuartos.execute(request);
        } else if (acao.equals("InserirQuarto")) {
            jsp = InserirQuarto.execute(request);
        } else if (acao.equals("BuscarQuarto")) {
            jsp = BuscarQuarto.execute(request);
        } else if (acao.equals("AlterarQuarto")) {
            jsp = AlterarQuarto.execute(request);
        } else if (acao.equals("ExcluirQuarto")) {
            jsp = ExcluirQuarto.execute(request);
        }

        // Cargos
        else if (acao.equals("ListarCargos")) {
            jsp = ListarCargos.execute(request);
        } else if (acao.equals("InserirCargo")) {
            jsp = InserirCargo.execute(request);
        } else if (acao.equals("BuscarCargo")) {
            jsp = BuscarCargo.execute(request);
        } else if (acao.equals("AlterarCargo")) {
            jsp = AlterarCargo.execute(request);
        } else if (acao.equals("ExcluirCargo")) {
            jsp = ExcluirCargo.execute(request);
        }

        // Usuarios
        else if (acao.equals("ListarUsuarios")) {
            jsp = ListarUsuarios.execute(request);
        } else if (acao.equals("InserirUsuario")) {
            jsp = InserirUsuario.execute(request);
        } else if (acao.equals("BuscarUsuario")) {
            jsp = BuscarUsuario.execute(request);
        } else if (acao.equals("AlterarUsuario")) {
            jsp = AlterarUsuario.execute(request);
        } else if (acao.equals("ExcluirUsuario")) {
            jsp = ExcluirUsuario.execute(request);
        }

        // Hospedes
        else if (acao.equals("ListarHospedes")) {
            jsp = ListarHospedes.execute(request);
        } else if (acao.equals("InserirHospede")) {
            jsp = InserirHospede.execute(request);
        } else if (acao.equals("BuscarHospede")) {
            jsp = BuscarHospede.execute(request);
        } else if (acao.equals("AlterarHospede")) {
            jsp = AlterarHospede.execute(request);
//        } else if (acao.equals("ExcluirHospede")) {
//            jsp = ExcluirHospede.execute(request);
        }

        // Areas de Serviço
        else if (acao.equals("ListarServicoAreas")) {
            jsp = ListarServicoAreas.execute(request);
        } else if (acao.equals("InserirServicoArea")) {
            jsp = InserirServicoArea.execute(request);
        } else if (acao.equals("BuscarServicoArea")) {
            jsp = BuscarServicoArea.execute(request);
        } else if (acao.equals("AlterarServicoArea")) {
            jsp = AlterarServicoArea.execute(request);
        } else if (acao.equals("ExcluirServicoArea")) {
            jsp = ExcluirServicoArea.execute(request);
        }

        // Serviços
        else if (acao.equals("ListarServicos")) {
            jsp = ListarServicos.execute(request);
        } else if (acao.equals("InserirServico")) {
            jsp = InserirServico.execute(request);
        } else if (acao.equals("BuscarServico")) {
            jsp = BuscarServico.execute(request);
        } else if (acao.equals("AlterarServico")) {
            jsp = AlterarServico.execute(request);
        } else if (acao.equals("ExcluirServico")) {
            jsp = ExcluirServico.execute(request);
        }

        //Redirecionando pagina
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
