<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.CategoriaQuarto"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.ItemConforto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Categorias de Quartos</title>

        <!-- CSS -->
        <!-- Google Icon Font -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Materialize CSS -->
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/materialize/materialize.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/padrao-tipo-1.css"/>
    
        <!--  Script -->
        <!-- Import jQuery before Materialize JS  -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/materialize/materialize.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/quartos-categorias.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Exclusão de Categorias de Quartos</h4>
          
            <form id="frmDeleteItem" method="post">				
                <%
                    CategoriaQuarto categoriaExcluir = null;
                    String codCategoriaExcluir = "";
                    String nomCategoriaExcluir = "";
                    Double vlrDiariaExcluir = null;
                    List<ItemConforto> listaItens = null;
                    
                    if (request.getAttribute("categoriaQuarto") != null) {
                        categoriaExcluir = (CategoriaQuarto)request.getAttribute("categoriaQuarto");
                        codCategoriaExcluir = categoriaExcluir.getCodCategoria();
                        nomCategoriaExcluir = categoriaExcluir.getNomCategoria();
                        vlrDiariaExcluir = categoriaExcluir.getVlrDiaria();
                        ArrayList arrayItensCategoria = (ArrayList)request.getAttribute("arrayItensCategoria");
                    }
                %>   
                <div id="container" class="row">
                    <p class="p-on-delete-page">Tem certeza que deseja excluir a categoria de quarto mostrada abaixo?</p>
                </div>
                
                <table class="striped">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nome</th>
                            <th>Valor da Diária</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><% out.print(codCategoriaExcluir); %></td>
                            <td><% out.print(nomCategoriaExcluir); %></td>
                            <td>R$ <% out.print(vlrDiariaExcluir); %></td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="card-action right-align button-box">
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="executeDeleteDialog('<%out.print(codCategoriaExcluir);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Excluir</button>
                    <a href="/hosten/servletweb?acao=ListarCategoriasQuarto"><button id="cancel-button" class="btn waves-effect waves-light" type="button"><i class="material-icons left">highlight_off</i>Cancelar</button></a>
                </div>
            </form>
        </main>

        <footer>
            <div class="footer-copyright">
                <div class="container">
                    Feito com  <i class="tiny material-icons">favorite_border</i> por estudantes do CEFET-MG
                </div>
            </div>
        </footer>
    </body>
</html>
