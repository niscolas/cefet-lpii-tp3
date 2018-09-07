<%@page import="java.util.List"%>
<%@page import="br.cefetmg.inf.hosten.model.domain.Cargo"%>
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
            <h4 class="title">Edição de Funcionários</h4>
            
            <form id="frmEditItem" method="post">
                <%
                    Usuario usuarioEditar = null;
                    String codUsuarioEditar = "";
                    String nomUsuarioEditar = "";
                    String desEmailUsuarioEditar = "";
                    String desSenhaUsuarioEditar = "";
                    String codCargoUsuarioEditar = "";
                    if (request.getAttribute("usuario") != null) {
                        usuarioEditar = (Usuario)request.getAttribute("usuario");
                        codUsuarioEditar = usuarioEditar.getCodUsuario();
                        nomUsuarioEditar = usuarioEditar.getNomUsuario();
                        desEmailUsuarioEditar = usuarioEditar.getDesEmail();
                        desSenhaUsuarioEditar = usuarioEditar.getDesSenha();
                        codCargoUsuarioEditar = usuarioEditar.getCodCargo();
                    }
                %>
                <div id="container" class="row">
                     <div class="col s12 form-input">
                         <div class="input-field">
                             <i class="material-icons prefix">filter_3</i>
                             <label for="codUsuario">Código</label>
                             <input id="codUsuario" name="codUsuario" type="number" value="<% out.print(codUsuarioEditar); %>" class="validate" required>
                         </div>
                     </div>
                 </div>
                 <div id="container" class="row">
                     <div class="col s12 form-input">
                         <div class="input-field">
                             <i class="material-icons prefix">person</i>
                             <label for="nomUsuario">Nome</label>
                             <input id="nomUsuario" name="nomUsuario" type="text" value="<% out.print(nomUsuarioEditar); %>" class="validate" required>
                         </div>
                     </div>
                 </div>
                 <div id="container" class="row">
                     <div class="col s12 form-input">
                         <div class="input-field">
                             <i class="material-icons prefix">email</i>
                             <label for="desEmail">Email</label>
                             <input id="desEmail" name="desEmail" type="email" value="<% out.print(desEmailUsuarioEditar); %>" class="validate" required>
                         </div>
                     </div>
                 </div>
                 <div id="container" class="row">
                     <div class="col s12 form-input">
                         <div class="input-field">
                             <i class="material-icons prefix">lock</i>
                             <label for="desSenha">Senha</label>
                             <input id="desSenha" name="desSenha" type="password" class="validate" required>
                         </div>
                     </div>
                 </div>
                 <div id="container" class="row">
                     <div class="col s12 form-input">
                         <div class="input-field">
                             <i class="material-icons prefix">local_offer</i>
                             <select name="codCargo">
                                    <%
                                        if (listaCargos != null) {
                                            for(Cargo cargo : listaCargos) {
                                                String codCargo = cargo.getCodCargo();
                                                String nomCargo = cargo.getNomCargo();
                                    %>          <option value="<% out.print(codCargo); %>"><% out.print(nomCargo);%></option>
                                    <%      
                                            } //for
                                        } //if
                                    %>
                             </select>
                             <label>Cargo</label>
                         </div>
                     </div>
                </div>
                <div class="card-action right-align button-box">
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="saveEditDialog('<%out.print(codUsuarioEditar);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Salvar alterações</button>
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
