<%@page import="br.cefetmg.inf.hosten.model.domain.Cargo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Mostra para o browser que o site é otimizado para mobile -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Cargos</title>

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
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/cargos.js"></script>
    </head>
    
    <body>
        <header>
            <%@include file="menu.jsp"%>
        </header>

        <main>            
            <h4 class="title">Edição de Cargos</h4>            
            
            <form id="frmEditItem" method="post">
                <%
                    Cargo cargoEditar = null;
                    String codCargoEditar = "";
                    String nomCargoEditar = "";
                    if (request.getAttribute("cargo") != null) {
                        cargoEditar = (Cargo)request.getAttribute("cargo");
                        codCargoEditar = cargoEditar.getCodCargo();
                        nomCargoEditar = cargoEditar.getNomCargo();
                    }
                %>
                <div class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">filter_3</i>
                            <label for="codCargo">Código</label>
                            <input id="codCargo" name="codCargo" type="number" value="<% out.print(codCargoEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">description</i>
                            <label for="nomCargo">Nome</label>
                            <input id="nomCargo" name="nomCargo" type="text" value="<% out.print(nomCargoEditar); %>" class="validate" required>
                        </div>
                    </div>
                </div>
                <!-- <div id="container" class="row">
                    <div class="col s12 form-input">
                        <div class="input-field">
                            <i class="material-icons prefix">assignment_turned_in</i>                          
                            <select multiple name="codPrograma">
                                <%	
                                    // for(int i = 0; i < programasEncontrados.length; i++) {
                                        // String codPrograma = programasEncontrados[i].getCodPrograma();
                                        // String desPrograma = programasEncontrados[i].getDesPrograma();
                                %>	<option value="<% // out.print(codPrograma); %>"><% // out.print(desPrograma);%></option>
                                <% // } %>
                            </select>
                            <label>Telas com acesso permitido</label>
                        </div>
                    </div>
                </div> -->
                <div class="card-action right-align button-box">
                    <button id="submit-button" class="btn waves-effect waves-light" onclick="saveEditDialog('<%out.print(codCargoEditar);%>')" type="submit"><i class="material-icons left">check_circle_outline</i>Salvar alterações</button>
                    <a href="/hosten/servletweb?acao=ListarCargos"><button id="cancel-button" class="btn waves-effect waves-light" type="button"><i class="material-icons left">highlight_off</i>Cancelar</button></a>
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
