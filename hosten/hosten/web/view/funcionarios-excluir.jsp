<%@page import="br.cefetmg.inf.hosten.model.domain.Cargo"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Cargo> listaCargos = null;
    
    if ((request.getAttribute("listaCargos")) != null) {
        listaCargos = (List<Cargo>)request.getAttribute("listaCargos");
    }
%>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Visualização de Funcionários</title>

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
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/funcionarios.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Exclusão de Funcionários</h4>
            
            <form id="frmDeleteItem" method="post">
                <%
                    Usuario usuarioExcluir = null;
                    String codUsuarioExcluir = "";
                    String nomUsuarioExcluir = "";
                    String desEmailUsuarioExcluir = "";
                    String codCargoUsuarioExcluir = "";
                    String nomCargoUsuarioExcluir = "";
                    if (request.getAttribute("usuario") != null) {
                        usuarioExcluir = (Usuario)request.getAttribute("usuario");
                        codUsuarioExcluir = usuarioExcluir.getCodUsuario();
                        nomUsuarioExcluir = usuarioExcluir.getNomUsuario();
                        desEmailUsuarioExcluir = usuarioExcluir.getDesEmail();
                        codCargoUsuarioExcluir = usuarioExcluir.getCodCargo();
                        
                        if (listaCargos != null) {
                            for(Cargo cargo : listaCargos) {
                                String codCargo = cargo.getCodCargo();
                                if (codCargo.equals(codCargoUsuarioExcluir)){
                                    nomCargoUsuarioExcluir = cargo.getNomCargo();
                                }
                            }
                        }
                    }
                %>
                <div id="container" class="row">
                    <p class="p-on-delete-page">Tem certeza que deseja excluir o funcionário mostrado abaixo?</p>
                </div>
                
                <table class="striped">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Cargo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><% out.print(codUsuarioExcluir); %></td>
                            <td><% out.print(nomUsuarioExcluir); %></td>
                            <td><% out.print(desEmailUsuarioExcluir); %></td>
                            <td><% out.print(nomCargoUsuarioExcluir); %></td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="card-action right-align button-box">
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="executeDeleteDialog('<%out.print(codUsuarioExcluir);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Excluir</button>
                    <a href="/hosten/servletweb?acao=ListarUsuarios"><button id="cancel-button" class="btn waves-effect waves-light" type="button"><i class="material-icons left">highlight_off</i>Cancelar</button></a>
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
